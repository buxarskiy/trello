package uz.pdp.trellowebsite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.pdp.trellowebsite.entity.User;
import uz.pdp.trellowebsite.repo.UserRepo;
import uz.pdp.trellowebsite.service.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User, UUID> {

    public final UserRepo userRepo;

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public void update(User user, UUID uuid) {

    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }


    public UserDetails findByUserName(String username) {
        return userRepo.findByUserName(username);
    }

    public Optional<User> findById(UUID id) {
        return userRepo.findById(id);
    }

}
