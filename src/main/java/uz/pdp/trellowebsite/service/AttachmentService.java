package uz.pdp.trellowebsite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.trellowebsite.entity.Attachment;
import uz.pdp.trellowebsite.repo.AttachmentRepo;
import uz.pdp.trellowebsite.service.base.BaseService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService implements BaseService<Attachment, UUID> {
    private final AttachmentRepo attachmentRepo;

    @Override
    public void save(Attachment attachment) {
        attachmentRepo.save(attachment);
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public void update(Attachment attachment, UUID uuid) {

    }

    @Override
    public List<Attachment> findAll() {
        return null;
    }
}
