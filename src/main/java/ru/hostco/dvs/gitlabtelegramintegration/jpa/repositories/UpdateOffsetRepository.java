package ru.hostco.dvs.gitlabtelegramintegration.jpa.repositories;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hostco.dvs.gitlabtelegramintegration.jpa.models.UpdateOffset;

public interface UpdateOffsetRepository extends JpaRepository<UpdateOffset, Integer> {

  @Query("select u from UpdateOffset u order by u.dateInsert desc")
  Page<UpdateOffset> findAllOrderByDateInsertDesc(@NonNull Pageable pageable);
}