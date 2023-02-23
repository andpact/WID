package andpact.project.wid.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @Column(length = 20, nullable = false)
    private String user;
    @Column(nullable = false)
    private LocalDate date;
    @Column(length = 20, nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalTime start;
    @Column(nullable = false)
    private LocalTime finish;

//    @Column(nullable = true)
//    private int degree;

//    private boolean complete = false;
    public void changeTitle(String newTitle) {
        this.title = newTitle;
    }
    public void changeStart(LocalTime newStart) {
        this.start = newStart;
    }
    public void changeFinish(LocalTime newFinish) {
        this.finish = newFinish;
    }
}