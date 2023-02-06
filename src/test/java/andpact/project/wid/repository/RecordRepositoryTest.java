package andpact.project.wid.repository;

import andpact.project.wid.entity.Record;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RecordRepositoryTest {
    @Autowired
    private RecordRepository recordRepository;
    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Record newRecord = Record.builder()
                    .title("repoInsertTest" + i)
                    .user("user" + i)
                    .date(LocalDate.of(2022, (i % 12) + 1, (i % 30) + 1))
                    .start(LocalTime.of((i % 24) + 1, (i % 60) + 1))
                    .finish(LocalTime.of((i % 24) + 2, (i % 60) + 2))
                    .build();
            recordRepository.save(newRecord);
        });
    }
    @Test
    public void selectTest() {
        Long rno = 1L;
        Optional<Record> result = recordRepository.findById(rno);
        Record record = result.orElseThrow();
        log.info(record);
    }
    @Test
    public void selectAllTest() {
        List<Record> recordList = recordRepository.findAll();
    }
    @Test
    public void updateTest() {
        Long rno = 1L;
        Optional<Record> result = recordRepository.findById(rno);
        Record record = result.orElseThrow();
        record.changeTitle("repoUpdateTest");
        record.changeStart(LocalTime.now());
        record.changeFinish(LocalTime.now());
        recordRepository.save(record);
        log.info(record);
    }
    @Test
    public void deleteTest() {
        Long rno = 1L;

    }
}
