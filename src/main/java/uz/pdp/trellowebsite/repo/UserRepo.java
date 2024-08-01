package uz.pdp.trellowebsite.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.trellowebsite.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    User findByUserName(String userName);
}
