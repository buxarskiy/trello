package uz.pdp.trellowebsite.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.trellowebsite.entity.Comment;

import java.util.List;
import java.util.UUID;


public interface CommentRepo extends JpaRepository<Comment, UUID> {


    @Query(value = "select * from comment c where c.task_id = ?1", nativeQuery = true)
    List<Comment> findByTaskId(UUID id);


    @Transactional
    @Modifying
    @Query(value = "delete from comment where id = ?1 and  task_id = ?2", nativeQuery = true)
    void deleteCommentIdByTaskId(UUID commentId, UUID TaskId);

}
