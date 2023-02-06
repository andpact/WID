package andpact.project.wid.controller;

import andpact.project.wid.dto.RecordDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RecordControllerTest {
    @Autowired
    private RecordController recordController;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 5).forEach(i -> {
            RecordDTO newRecordDTO = RecordDTO.builder()
                    .title("conCreateTest" + i)
                    .user("user" + i)
                    .date(LocalDate.of(2022, (i % 12) + 1, (i % 30) + 1))
                    .start(LocalTime.of((i % 24) + 1, (i % 60) + 1))
                    .finish(LocalTime.of((i % 24) + 2, (i % 60) + 2))
                    .build();
            recordController.create(newRecordDTO);
        });
    }
    @Test
    public void readTest() {
        Long rno = 1L;
        RecordDTO recordDTO = recordController.read(rno);
        log.info(recordDTO);
    }
    @Test
    public void readAllTest() {
        recordController.readAll();
    }
    @Test
    public void updateTest() {
        Long rno = 1L;
        RecordDTO recordDTO = recordController.read(rno);
        recordDTO.setTitle("conUpdateTest");
        recordDTO.setStart(LocalTime.now());
        recordDTO.setFinish(LocalTime.now());
        recordController.update(rno, recordDTO);
        log.info(recordDTO);
    }
    @Test
    public void deleteTest() {
        Long rno = 1L;
        recordController.delete(rno);
    }
}
