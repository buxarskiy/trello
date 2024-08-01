package uz.pdp.trellowebsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.pdp.trellowebsite.entity.enums.TaskStatus;


import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @CreationTimestamp
    private LocalDateTime startTime;
    private LocalDateTime deadLine;
    @ManyToMany
    private List<User> users;
    @ManyToOne
    private Attachment attachment;
    private LocalDateTime isFinishTime;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus = TaskStatus.STARTED;

    private Integer index;

    // Static counter to keep track of the index
    private static Integer counter = 1;

    // Synchronized method to increment and get the counter safely
    private synchronized static Integer getNextCounter() {
        return counter++;
    }

    // Method annotated with @PrePersist to set the index before persisting
    @PrePersist
    protected void onCreate() {
        this.index = getNextCounter();
    }
}
