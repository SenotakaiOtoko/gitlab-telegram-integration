package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Update {

  @JsonProperty("update_id")
  Long updateId;
  Message message;
  @JsonProperty("inline_query")
  InlineQuery inlineQuery;
  @JsonProperty("chosen_inline_result")
  ChosenInlineResult chosenInlineResult;
  @JsonProperty("callback_query")
  CallbackQuery callbackQuery;
}
