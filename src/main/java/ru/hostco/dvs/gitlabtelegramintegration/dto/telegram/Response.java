package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import lombok.Data;

@Data
public class Response<T> {
  Boolean ok;
  T result;
}
