package uz.pdp.trellowebsite.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.trellowebsite.entity.User;
import uz.pdp.trellowebsite.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RunnerClass implements CommandLineRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            generateData();
        }
    }

    private void generateData() {
        User user1 = User.builder().userName("admin").password(passwordEncoder.encode("123")).fullName("Admin").build();
        User user2 = User.builder().userName("user").password(passwordEncoder.encode("123")).fullName("User").build();
        User user3 = User.builder().userName("userxon").password(passwordEncoder.encode("123")).fullName("Userxon").build();
        User user4 = User.builder().userName("useroy").password(passwordEncoder.encode("123")).fullName("Useroy").build();
        User user5 = User.builder().userName("userbek").password(passwordEncoder.encode("123")).fullName("Userbek").build();
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
    }
}
