package andpact.project.wid.repository;

import andpact.project.wid.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {

    @Query("select q from Quest q where q.date = :date and q.mID = :mID") // ':'뒤에 공백 있으면 안된다.
    List<Quest> findAllByDateAndMID(LocalDate date, String mID);

    @Query("select q from Quest q where q.mID = :mID")
    List<Quest> findAllByMID(String mID);
}
