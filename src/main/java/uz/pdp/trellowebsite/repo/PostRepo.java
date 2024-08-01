package uz.pdp.trellowebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.trellowebsite.entity.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepo extends JpaRepository<Post, UUID> {

    @Query(value = "select p.* from post p order by p.index", nativeQuery = true)
    List<Post> findAllByOrderById();

    Optional<Post> findById(UUID id);

//    List<Post> findAllTaskAmount(UUID id);
}
