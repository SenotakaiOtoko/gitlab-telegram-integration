package ru.hostco.dvs.gitlabtelegramintegration.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hostco.dvs.gitlabtelegramintegration.config.ApplicationConfiguration;
import ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab.GitLabUser;
import ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab.MergeRequestInfo;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class Syncer {

  private final GitLabClient gitLabClient;
  private final ApplicationConfiguration applicationConfiguration;

  @Scheduled(fixedDelay = 6 * 1000)
  public void updateMergeRequests() {
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
        });

    log.info("mergeRequests");
  }
}
