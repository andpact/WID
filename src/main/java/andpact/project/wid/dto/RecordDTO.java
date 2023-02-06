package andpact.project.wid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    Long rno;
    String user;
    String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmm", timezone = "Asia/Seoul")
    LocalTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HHmm", timezone = "Asia/Seoul")
    LocalTime finish;
}
