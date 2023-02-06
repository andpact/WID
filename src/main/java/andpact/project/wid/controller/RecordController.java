package andpact.project.wid.controller;

import andpact.project.wid.dto.RecordDTO;
import andpact.project.wid.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
@Log4j2
public class RecordController {
    private final RecordService recordService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long create(@RequestBody RecordDTO newRecordDTO) {
        log.info(newRecordDTO);
        Long rno = recordService.create(newRecordDTO);
        return rno;
    }

    @GetMapping(value = "/{rno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RecordDTO read(@PathVariable(value = "rno") Long rno) {
        log.info("read rno:" + rno);
        RecordDTO recordDTO = recordService.read(rno);
        log.info(recordDTO);
        return recordDTO;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecordDTO> readAll() {
        List<RecordDTO> recordDTOList = recordService.readAll();
        return recordDTOList;
    }

    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable(value = "rno") Long rno, @RequestBody RecordDTO recordDTO) {
        recordDTO.setRno(rno);
        recordService.update(recordDTO);
    }

    @DeleteMapping(value = "/{rno}")
    public void delete(@PathVariable(value = "rno") Long rno) {
        recordService.delete(rno);
    }

}
