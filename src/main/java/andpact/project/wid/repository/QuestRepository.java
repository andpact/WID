package andpact.project.wid.repository;

import andpact.project.wid.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
