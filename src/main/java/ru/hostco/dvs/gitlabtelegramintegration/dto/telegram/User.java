package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
public class User {

  Long id;
  @JsonProperty("first_name")
  String firstName;
  @JsonProperty("last_name")
  String lastName;
  String username;
}
