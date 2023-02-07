package andpact.project.wid.entity;

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
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qno;
    @Column(length = 20, nullable = false)
    private String user;
    @Column(nullable = false)
    private LocalDate date;
    @Column(length = 20, nullable = false)
    private String category;
    @Column(length = 20, nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalTime start;
    @Column(nullable = false)
    private LocalTime finish;
    @Column(nullable = false)
    private int degree;

    public void changeTitle(String newTitle) {
        this.title = newTitle;
    }
    public void changeStart(LocalTime newStart) {
        this.start = newStart;
    }
    public void changeFinish(LocalTime newFinish) {
        this.finish = newFinish;
    }
    public void changeDegree(int newDegree) {
        this.degree = newDegree;
    }
}