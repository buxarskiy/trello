package uz.pdp.trellowebsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Task> tasks;

    @CreationTimestamp
    private Timestamp timestamp;

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

