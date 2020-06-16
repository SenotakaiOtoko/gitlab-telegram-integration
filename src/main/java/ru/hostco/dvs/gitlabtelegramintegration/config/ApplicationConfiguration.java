package ru.hostco.dvs.gitlabtelegramintegration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationConfiguration {
  /**
   * Токен для доступа к gitlab api
   */
  private String gitlabPrivateToken;
}
