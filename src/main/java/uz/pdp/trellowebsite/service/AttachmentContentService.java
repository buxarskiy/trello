package uz.pdp.trellowebsite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.trellowebsite.entity.AttachmentContent;
import uz.pdp.trellowebsite.repo.AttachmentContentRepo;
import uz.pdp.trellowebsite.service.base.BaseService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentContentService implements BaseService<AttachmentContent, UUID> {
    private final AttachmentContentRepo attachmentContentRepo;

    @Override
    public void save(AttachmentContent attachmentContent) {
        attachmentContentRepo.save(attachmentContent);
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public void update(AttachmentContent attachmentContent, UUID uuid) {

    }

    @Override
    public List<AttachmentContent> findAll() {
        return attachmentContentRepo.findAll();
    }

    public List<byte[]> getFiles(UUID attachmentId) {
        return attachmentContentRepo.getAllFilesByAttachmentId(attachmentId);
    }

    public void deleteByAttachmentIdAndTaskId(UUID attachmentId) {
        attachmentContentRepo.deleteByTaskIdAndAttachmentId(attachmentId);
    }
}
