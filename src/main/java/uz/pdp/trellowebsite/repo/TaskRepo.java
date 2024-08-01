package uz.pdp.trellowebsite.repo;

import jakarta.transaction.Transactional;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.trellowebsite.entity.Task;
import uz.pdp.trellowebsite.entity.User;
import uz.pdp.trellowebsite.responce.RespClass;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepo extends JpaRepository<Task, UUID> {
    @Transactional
    @Modifying
    @Query(value = "delete from task_users ts where users_id = ?1 and task_id = ?2", nativeQuery = true)
    void deleteUserById(UUID userId, UUID taskId);

    @Transactional
    @Modifying
    @Query(value = "select t.* from task t order by t.index", nativeQuery = true)
    List<Task> findAllByOrderId();


    @Transactional
    @Modifying
    @Query(value = "select * from task t where t.id = ?1", nativeQuery = true)
    List<Task> findByDeadLine(UUID taskId);

    @Transactional
    @Modifying
    @Query(value = "delete from post_tasks pt where post_id = ?1 and tasks_id = ?2", nativeQuery = true)
    void deleteTaskById(UUID postId, UUID taskId);

//    @Transactional
//    @Modifying
//    @Query(value = "delete from post_task pt")


    @Transactional
    @Modifying
    @Query(value = "update task set dead_line = null where id = ?1", nativeQuery = true)
    void deleteDeadlineByTaskId(UUID taskId);


    @Query(value = "SELECT COUNT(t.id) " +
            "FROM task t " +
            "JOIN task_users ut ON t.id = ut.task_id " +
            "WHERE ut.users_id = ?1", nativeQuery = true)
    int countTasksByUser(UUID userId);

    @Query(value = "SELECT COUNT(t.id) " +
            "FROM task t " +
            "JOIN task_users ut ON t.id = ut.task_id " +
            "WHERE ut.users_id = ?1 AND t.dead_line < CURRENT_TIMESTAMP", nativeQuery = true)
    int countCompletedTasksByUser(UUID userId);

    @Query(value = "SELECT COUNT(t.id) " +
            "FROM task t " +
            "JOIN task_users ut ON t.id = ut.task_id " +
            "WHERE ut.users_id = ?1 AND t.dead_line >= CURRENT_TIMESTAMP AND t.task_status = 'COMPLETED'", nativeQuery = true)
    int countPendingTasksByUser(UUID userId);


}
