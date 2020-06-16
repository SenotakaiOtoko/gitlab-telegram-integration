package ru.hostco.dvs.gitlabtelegramintegration.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hostco.dvs.gitlabtelegramintegration.config.ApplicationConfiguration;
import ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab.GitLabUser;
import ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab.MergeRequestInfo;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.Chat;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.Message;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.Response;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.Update;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class Syncer {

  private final GitLabClient gitLabClient;
  private final ApplicationConfiguration applicationConfiguration;
  private final TelegramBotClient telegramBotClient;

  //@Scheduled(fixedDelay = 6 * 1000)

  @Scheduled(fixedDelay = 6 * 1000)
  public void updateMergeRequests() {
    final String me = telegramBotClient.getMe(applicationConfiguration.getTelegramBotToken());

    final Response<Collection<Update>> updates = telegramBotClient
        .getUpdates(applicationConfiguration.getTelegramBotToken());


    final List<Message> telegramToGitlabUsernames = updates.getResult().stream()
        .map(Update::getMessage).filter(Objects::nonNull)
        .filter(m -> m.getText().matches("/gitlabusername \\w+")).collect(Collectors.toList());

    /**
     * TODO: Сохранять эти данные в БД
     */
    final HashMap<String, Chat> gitlabToTelegramUser = new HashMap<>();

    telegramToGitlabUsernames.stream().forEach(o -> {
      final String gitlabUsername = o.getText().replaceAll("/gitlabusername\\s+", "");
      gitlabToTelegramUser.put(gitlabUsername, o.getChat());
    });

    final String gitlabPrivateToken = applicationConfiguration.getGitlabPrivateToken();



    final GitLabUser currentUserInfo = gitLabClient
        .getCurrentUserInfo(gitlabPrivateToken);

    final Collection<MergeRequestInfo> mergeRequestInfos = gitLabClient
        .getMergeRequests(gitlabPrivateToken, "assigned_to_me");

    mergeRequestInfos.stream().filter(m -> m.getMergedBy() == null)
        .forEach(m -> {
          final Collection<GitLabUser> gitLabUsers = gitLabClient
              .getProjectMembers(gitlabPrivateToken, m.getProjectId());

          /**
           * Отфильтровываем среди пользователей тех, на кого можно назначить МР
           */
          final List<GitLabUser> usersCanBeAssigned = gitLabUsers.stream()
              .filter(u -> !currentUserInfo.equals(u) && !u.equals(m.getAuthor())
                  && u.getAccessLevel() >= 30).collect(Collectors.toList());

          /**
           * Выбираем человека, на которого назначим МР
           */
          final GitLabUser newAssignee = usersCanBeAssigned.stream()
              .skip((int) (usersCanBeAssigned.size() * Math.random()))
              .findAny().get();

          /**
           * Назначаем на выбранного участника проекта(не на себя и не на автора)
           */
          gitLabClient
              .changeMergeRequestAssignee(gitlabPrivateToken, m.getProjectId(), m.getIid(),
                  newAssignee.getId());

          /**
           * Отправляем уведомление и ссылку на merge request
           */
          final Chat assigneeChat = gitlabToTelegramUser.get(newAssignee.getUsername());
          if (assigneeChat != null) {
            final StringBuilder text = new StringBuilder()
                .append(assigneeChat.getUsername())
                .append(", на вас назначен МР: \n")
                .append(m.getWebUrl());


            telegramBotClient
                .sendMessage(applicationConfiguration.getTelegramBotToken(), assigneeChat.getId(),
                    text.toString());
          }
        });

    log.info("mergeRequests");
  }
}
