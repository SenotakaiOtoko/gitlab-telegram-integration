package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CallbackQuery {
  String id;
  User from;
  Message message;
  @JsonProperty("inline_message_id")
  String inlineMessageId;
  String data;
}
