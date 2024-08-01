package uz.pdp.trellowebsite.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.trellowebsite.entity.Attachment;
import uz.pdp.trellowebsite.entity.AttachmentContent;
import uz.pdp.trellowebsite.entity.Task;
import uz.pdp.trellowebsite.repo.CommentRepo;
import uz.pdp.trellowebsite.repo.TaskRepo;
import uz.pdp.trellowebsite.service.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService implements BaseService<Task, UUID> {
    private final TaskRepo taskRepo;
    private final CommentRepo commentRepo;
    private final AttachmentContentService attachmentContentService;
    private final AttachmentService attachmentService;

    @Override
    public void save(Task task) {
        taskRepo.save(task);
    }

    @Override
    public void delete(UUID uuid) {
        taskRepo.deleteById(uuid);
    }

    @Override
    public void update(Task task, UUID uuid) {

    }

    @Override
    public List<Task> findAll() {
        return taskRepo.findAllByOrderId();
    }


    public Optional<Task> findById(UUID id) {
        return taskRepo.findById(id);
    }

    public void DeleteUserById(UUID userId, UUID taskId) {
        taskRepo.deleteUserById(userId, taskId);
    }


    public List<Task> findByDeadLine(UUID id) {
        return taskRepo.findByDeadLine(id);
    }

    public void deleteTaskByPostId(UUID postId, UUID taskId) {
        taskRepo.deleteTaskById(postId, taskId);
    }

    public void deleteDeadlineByTaskId(UUID taskId) {
        taskRepo.deleteDeadlineByTaskId(taskId);
    }

    @SneakyThrows
    public void saveFile(UUID taskId, MultipartFile file) {
        Attachment attachment = new Attachment();
        Optional<Task> optionalTask = findById(taskId);

        if (!file.isEmpty()) {
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                attachment.setName(file.getOriginalFilename());
                attachment.setContentType(file.getContentType());
                attachmentService.save(attachment);

                AttachmentContent content = new AttachmentContent();
                content.setAttachment(attachment);
                content.setContent(file.getInputStream().readAllBytes());
                attachmentContentService.save(content);
                task.setAttachment(attachment);
                task.setId(taskId);
                save(task);
            }


        }
    }
}
