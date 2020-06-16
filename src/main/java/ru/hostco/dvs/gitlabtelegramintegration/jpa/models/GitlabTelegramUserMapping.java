package ru.hostco.dvs.gitlabtelegramintegration.jpa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rybakov Create at 2020-06-16 12:36
 */
@Entity
@Table(name = "gitlab_telegram_user_mapping", schema = "gitlabtelegramintegration")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class GitlabTelegramUserMapping {

  /**
   *
   */
  @Id
  @Column(name = "id")
  @Setter
  @SequenceGenerator(schema = "gitlabtelegramintegration", name = "GitlabTelegramUserMappingIdSeq", sequenceName = "gitlab_telegram_user_mapping_id_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GitlabTelegramUserMappingIdSeq")
  private Long id;

  /**
   * Имя пользователя telegram
   */
  @Column(name = "telegram_username", nullable = false)
  private String telegramUsername;

  /**
   *
   */
  @Column(name = "telegram_first_name")
  private String telegramFirstName;

  /**
   *
   */
  @Column(name = "telegram_last_name")
  private String telegramLastName;

  /**
   * Идентификатор чата с пользователем
   */
  @Column(name = "telegram_chat_id", nullable = false)
  private Long telegramChatId;

  /**
   * Имя пользователя в gitlab
   */
  @Column(name = "gitlab_username", nullable = false)
  private String gitlabUsername;
}