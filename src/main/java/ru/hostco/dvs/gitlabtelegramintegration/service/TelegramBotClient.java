package ru.hostco.dvs.gitlabtelegramintegration.service;

import java.util.Collection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.Message;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.Response;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.Update;

@FeignClient(value = "telegram-bot-api", url = "https://api.telegram.org")
public interface TelegramBotClient {

  @RequestMapping(value = "bot{token}/getMe", method = RequestMethod.GET)
  String getMe(@PathVariable String token);

  @RequestMapping(value = "bot{token}/getUpdates", method = RequestMethod.GET)
  Response<Collection<Update>> getUpdates(@PathVariable String token);

  @RequestMapping(value = "bot{token}/sendMessage", method = RequestMethod.POST)
  Response<Message> sendMessage(
      @PathVariable String token,
      @RequestParam(value = "chat_id", required = true) long chatId,
      @RequestParam(value = "text", required = true) String text);
}
