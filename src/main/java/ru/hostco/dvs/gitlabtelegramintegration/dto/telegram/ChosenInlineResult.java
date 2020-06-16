package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChosenInlineResult {
  @JsonProperty("result_id")
  String resultId;
  User from;
  @JsonProperty("inline_message_id")
  String inlineMessageId;
  String query;
}
