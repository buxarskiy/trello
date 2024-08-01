package uz.pdp.trellowebsite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.trellowebsite.entity.Post;
import uz.pdp.trellowebsite.entity.Task;
import uz.pdp.trellowebsite.service.CommentService;
import uz.pdp.trellowebsite.service.PostService;
import uz.pdp.trellowebsite.service.TaskService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final PostService postService;
    private final TaskService taskService;
    private final CommentService commentService;

    @GetMapping("/")
    public String test(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<Post> posts = postService.findAllByOrderId();
        List<Task> tasks = taskService.findAll();
        for (Task task : tasks) {
            var comments = commentService.findByTaskId(task.getId());
            model.addAttribute("comments", comments);
        }
        for (Post post : posts) {
            post.getTasks().sort(Comparator.comparing(Task::getId));
        }
        model.addAttribute("posts", posts);
        model.addAttribute("tasks", tasks);
        model.addAttribute("authentication", authentication);
        model.addAttribute("time", LocalDateTime.now());
        return "home";
    }

   /* @GetMapping("/login")
    public String login() {
        return "login";
    }*/

   /* @GetMapping("/")
    public String test() {
        return "test";
    }*/
}