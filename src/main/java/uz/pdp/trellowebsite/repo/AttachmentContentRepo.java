package uz.pdp.trellowebsite.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.trellowebsite.entity.AttachmentContent;

import java.util.List;
import java.util.UUID;

public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, UUID> {
    @Query(value = "select a.content from attachment_content a where a.attachment_id = ?1", nativeQuery = true)
    List<byte[]> getAllFilesByAttachmentId(UUID attachmentId);

    @Transactional
    @Modifying
    @Query(value = "delete from attachment_content a where a.attachment_id = ?1", nativeQuery = true)
    void deleteByTaskIdAndAttachmentId(UUID attachmentId);
}
