package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import ru.hostco.dvs.gitlabtelegramintegration.dto.converter.ChatTypeConverter;

@Data
public class Chat {

  Long id;
  @JsonDeserialize(converter = ChatTypeConverter.class)
  ChatType type;
  String title;
  String username;
  @JsonProperty("first_name")
  String firstName;
  @JsonProperty("last_name")
  String lastName;
}
