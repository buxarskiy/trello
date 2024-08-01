package uz.pdp.trellowebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.trellowebsite.entity.Attachment;

import java.util.UUID;

public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
}
