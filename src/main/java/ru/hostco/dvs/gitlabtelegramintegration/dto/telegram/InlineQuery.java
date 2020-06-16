package ru.hostco.dvs.gitlabtelegramintegration.dto.telegram;

import lombok.Data;

@Data
public class InlineQuery {
  String id;
  User from;
  String query;
  String offset;
}
