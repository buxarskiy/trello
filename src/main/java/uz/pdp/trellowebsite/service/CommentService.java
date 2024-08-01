package uz.pdp.trellowebsite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.trellowebsite.entity.Comment;
import uz.pdp.trellowebsite.repo.CommentRepo;
import uz.pdp.trellowebsite.service.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService implements BaseService<Comment, UUID> {
    private final CommentRepo commentRepo;

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public void delete(UUID uuid) {
        commentRepo.deleteById(uuid);
    }

    @Override
    public void update(Comment comment, UUID uuid) {

    }

    @Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    public Optional<Comment> findById(UUID id) {
        return commentRepo.findById(id);
    }

    public List<Comment> findByTaskId(UUID id) {
        return commentRepo.findByTaskId(id);
    }

    public void deleteCommentByTaskId(UUID commentId, UUID taskId) {
        commentRepo.deleteCommentIdByTaskId(commentId, taskId);
    }

}
