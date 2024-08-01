package uz.pdp.trellowebsite.report;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.trellowebsite.entity.Post;
import uz.pdp.trellowebsite.entity.User;
import uz.pdp.trellowebsite.entity.enums.TaskStatus;
import uz.pdp.trellowebsite.service.PostService;
import uz.pdp.trellowebsite.service.TaskService;
import uz.pdp.trellowebsite.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("report")
@RequiredArgsConstructor
public class Report {
    private final PostService postService;
    private final TaskService taskService;
    private final UserService userService;

    /*@GetMapping("myTask")
    private String myTask(@AuthenticationPrincipal User user, Model model) {
        List<Post> posts = postService.findAllByUser(user.getId());
        model.addAttribute("loggedUser", user);
        model.addAttribute("posts", posts);
        return "redirect:/";
    }*/



    /*@GetMapping("report_1")
    private String report_1(Model model) {
        List<Report1> members = userService.findAllCountOfWork();
        model.addAttribute("members", members);
        return "report1";
    }*/

    /*@GetMapping("report_2")
    private String report_2(Model model) {
        List<Report2> posts = postService.findAllTaskAmount();
        model.addAttribute("post", posts);
        return "report2";
    }*/
}
