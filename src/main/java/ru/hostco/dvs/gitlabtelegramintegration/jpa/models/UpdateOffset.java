package ru.hostco.dvs.gitlabtelegramintegration.jpa.models;

import java.time.LocalDateTime;
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
@Table(name = "update_offset", schema = "gitlabtelegramintegration")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateOffset {

  /**
   *
   */
  @Id
  @Setter
  @Column(name = "id")
  @SequenceGenerator(schema = "gitlabtelegramintegration", name = "UpdateOffsetIdSeq", sequenceName = "update_offset_id_seq", allocationSize = 1, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UpdateOffsetIdSeq")
  private Long id;

  /**
   * Идентификатор последнего update'а
   */
  @Column(name = "update_id", nullable = false)
  private Long updateId;

  /**
   * Дата вставки значения
   */
  @Column(name = "date_insert", nullable = false, insertable = false)
  private LocalDateTime dateInsert;
}