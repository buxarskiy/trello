package uz.pdp.trellowebsite.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private byte[] content;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Attachment attachment;

    public String base64() {
        return Base64.getEncoder().encodeToString(content);
    }
}
