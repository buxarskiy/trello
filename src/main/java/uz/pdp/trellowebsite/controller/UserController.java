package uz.pdp.trellowebsite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.trellowebsite.entity.Task;
import uz.pdp.trellowebsite.entity.User;
import uz.pdp.trellowebsite.service.TaskService;
import uz.pdp.trellowebsite.service.UserService;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    public final UserService userService;
    public final TaskService taskService;

    @PostMapping("selectUser/{taskId}")
    public String selectUser(@PathVariable UUID taskId, @RequestParam UUID userId) {

        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Task> optionalTask = taskService.findById(taskId);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                task.getUsers().add(user);
                taskService.save(task);
            }
        }
        return "redirect:/task/editTask/" + taskId;
    }


}
