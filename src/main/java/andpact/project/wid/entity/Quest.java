package andpact.project.wid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qno;
    @Column(length = 20, nullable = false)
    private String user;
    @Column(length = 20, nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDate dueDate;
    @Column(nullable = false)
    private boolean finished;

    public void changeTitle(String newTitle) {
        this.title = newTitle;
    }
    public void changeDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }
    public void changeFinished(boolean finished) {
        this.finished = finished;
    }
}

