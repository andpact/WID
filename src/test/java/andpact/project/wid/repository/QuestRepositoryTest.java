package andpact.project.wid.repository;

import andpact.project.wid.entity.Quest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class QuestRepositoryTest {
    @Autowired
    private QuestRepository questRepository;
    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Quest newQuest = Quest.builder()
                    .title("repoInsertTest" + i)
                    .user("user" + i)
                    .dueDate(LocalDate.of(2022, (i % 12) + 1, (i % 30) + 1))
                    .finished(false)
                    .build();
            questRepository.save(newQuest);
        });
    }
    @Test
    public void selectTest() {
        Long qno = 2L;
        Optional<Quest> result = questRepository.findById(qno);
        Quest quest = result.orElseThrow();
        log.info(quest);
    }
    @Test
    public void selectAllTest() {
        List<Quest> questList = questRepository.findAll();
    }
    @Test
    public void updateTest() {
        Long qno = 2L;
        Optional<Quest> result = questRepository.findById(qno);
        Quest quest = result.orElseThrow();
        quest.changeTitle("repoUpdateTest");
        quest.changeDueDate(LocalDate.now());
        quest.changeFinished(true);
        questRepository.save(quest);
        log.info(quest);
    }
    @Test
    public void deleteTest() {
        Long qno = 13L;
        questRepository.deleteById(qno);
    }
}
