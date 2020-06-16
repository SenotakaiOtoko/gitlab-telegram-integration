package ru.hostco.dvs.gitlabtelegramintegration.dto.gitlab;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
public class GitLabUser {

  Long id;
  String name;
  String username;
  String state;
  @JsonProperty("web_url")
  String webUrl;
  @JsonProperty("avatar_url")
  String avatarUrl;
  @JsonProperty("access_level")
  Long accessLevel;
  @JsonProperty("expires_at")
  LocalDateTime expiresAt;
}
