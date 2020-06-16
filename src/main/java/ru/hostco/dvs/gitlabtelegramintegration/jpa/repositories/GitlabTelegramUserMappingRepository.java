package ru.hostco.dvs.gitlabtelegramintegration.jpa.repositories;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hostco.dvs.gitlabtelegramintegration.jpa.models.GitlabTelegramUserMapping;

public interface GitlabTelegramUserMappingRepository extends
    JpaRepository<GitlabTelegramUserMapping, Integer> {

  Page<GitlabTelegramUserMapping> findAllByTelegramUsernameEqualsIgnoreCase(
      @NonNull String telegramUsername, @NonNull Pageable pageable);

  Page<GitlabTelegramUserMapping> findAllByGitlabUsernameEqualsIgnoreCase(
      @NonNull String gitlabUsername, @NonNull Pageable pageable);

}