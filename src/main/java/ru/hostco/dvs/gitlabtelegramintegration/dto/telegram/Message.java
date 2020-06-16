package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class Message {

  @JsonProperty("message_id")
  Long messageId;
  User from;
  String text;
  LocalDate date;
  Chat chat;
}
