package ru.hostco.dvs.gitlabtelegramintegration.dto.converter;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.stereotype.Component;
import ru.hostco.dvs.gitlabtelegramintegration.dto.telegram.ChatType;

@Component
public class ChatTypeConverter extends StdConverter<String, ChatType> {

  @Override
  public ChatType convert(String s) {
    if (s == null) {
      return null;
    }
    return ChatType.valueOf(s.toUpperCase());
  }
}
