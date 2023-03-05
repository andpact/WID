package andpact.project.wid.repository;

import andpact.project.wid.domain.Quest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class QuestRepositoryTests {
    @Autowired
    private QuestRepository questRepository;
    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Quest newQuest = Quest.builder()
                    .mID("member" + i)
                    .date(LocalDate.now())
                    .category("repoInsertTest" + i)
                    .title("repoInsertTest" + i)
                    .description("repoInsertTest" + i)
                    .start(LocalTime.now())
                    .finish(LocalTime.now())
                    .degree(0)
                    .build();
            questRepository.save(newQuest);
        });
    }
    @Test
    public void selectTest() {
        Long qno = 87L;
        Optional<Quest> result = questRepository.findById(qno);
        Quest quest = result.orElseThrow();
        log.info(quest);
    }

    @Test
    public void selectAllByDateTest() {
        LocalDate date = LocalDate.of(2023, 3, 4);
        String mID = "member1";
        List<Quest> list = questRepository.findAllByDateAndMID(date, mID);
        log.info(list);
    }

    @Test
    public void selectAllByMIDTest() {
        String mID = "member1";
        List<Quest> questList = questRepository.findAllByMID(mID);
        log.info(questList);
    }
    @Test
    public void updateTest() {
        Long qno = 88L;
        Optional<Quest> result = questRepository.findById(qno);
        Quest quest = result.orElseThrow();
        quest.changeTitle("repoUpdateTest");
        quest.changeDescription("repoUpdateTest");
        quest.changeStart(LocalTime.now());
        quest.changeFinish(LocalTime.now());
        quest.changeDegree(100);
        questRepository.save(quest);
        log.info(quest);
    }
    @Test
    public void deleteTest() {
        Long qno = 13L;
        questRepository.deleteById(qno);
    }
}
