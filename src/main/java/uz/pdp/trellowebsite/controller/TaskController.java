package uz.pdp.trellowebsite.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.trellowebsite.entity.*;
import uz.pdp.trellowebsite.entity.enums.TaskStatus;
import uz.pdp.trellowebsite.service.*;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final PostService postService;
    private final HttpSession httpSession;
    private final CommentService commentService;
    private final UserService userService;
    private final AttachmentContentService attachmentContentService;

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);


    @PostMapping("createTask")
    private String addTask(@ModelAttribute Task task, @RequestParam("postId") UUID id) {
        logger.info("Adding task: " + task.getName());
        try {
            Post post = postService.findById(id).get();
            taskService.save(task);
            post.getTasks().add(task);
            postService.save(post);
            return "redirect:/";
        } catch (Exception e) {
            logger.error("Error adding task", e);
            return "error";
        }
    }

    @GetMapping("/editTask/{id}")
    public String editTask(@PathVariable UUID id, Model model) {
        Task currentTask = taskService.findById(id).get();
        model.addAttribute("currentTask", currentTask);
        List<Comment> comments = commentService.findByTaskId(id);
        List<Task> taskDeadlines = taskService.findByDeadLine(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<AttachmentContent> attachmentContents = attachmentContentService.findAll();

        List<User> users = userService.findAll();
        model.addAttribute("comments", comments);
        model.addAttribute("attachmentContents", attachmentContents);
        model.addAttribute("users", users);
        model.addAttribute("taskDeadlines", taskDeadlines);
        model.addAttribute("authentication", authentication);
        return "editTask";
    }

    @PostMapping("editTaskName/{currentTaskId}")
    public String editTaskName(@PathVariable UUID currentTaskId, @RequestParam String name) {
        Optional<Task> optionalTask = taskService.findById(currentTaskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setName(name);
            taskService.save(task);
        }
        return "redirect:/task/editTask/" + currentTaskId;
    }

    @PostMapping("editTaskDeadline/{currentTaskId}")
    public String editTaskDeadline(@PathVariable UUID currentTaskId, @RequestParam LocalDateTime deadLine) {
        Optional<Task> optionalTask = taskService.findById(currentTaskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDeadLine(deadLine);
            taskService.save(task);
        }
        return "redirect:/task/editTask/" + currentTaskId;
    }

    @PostMapping("comment/{currentTaskId}")
    public String comment(@PathVariable UUID currentTaskId, @ModelAttribute Comment comment, Model model) {

        Optional<Task> optionalTask = taskService.findById(currentTaskId);
        Task task = optionalTask.get();

        comment.setTask(task);
        comment.setDateTime(LocalDateTime.now());
        commentService.save(comment);
        return "redirect:/task/editTask/" + currentTaskId;
    }

    @PostMapping("deleteComment")
    public String deleteComment(@RequestParam(value = "commentId") UUID commentId, @RequestParam(name = "taskId") UUID taskId) {
        commentService.deleteCommentByTaskId(commentId, taskId);
        return "redirect:/task/editTask/" + taskId;
    }

    @PostMapping("delete-user")
    public String deleteUser(@RequestParam(value = "taskId") UUID taskId, @RequestParam(value = "userId") UUID userId) {
        taskService.DeleteUserById(userId, taskId);
        return "redirect:/task/editTask/" + taskId;
    }

    @PostMapping("deleteTask")
    private String deleteTask(@RequestParam(value = "taskId") UUID taskId, @RequestParam(value = "postId") UUID postId) {
        taskService.deleteTaskByPostId(postId, taskId);
        return "redirect:/";
    }

    @PostMapping("deleteDeadLine")
    public String deleteDeadline(@RequestParam(value = "taskId") UUID taskId) {
        taskService.deleteDeadlineByTaskId(taskId);
        return "redirect:/task/editTask/" + taskId;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam UUID taskId, @RequestParam(name = "file") MultipartFile file) {
        taskService.saveFile(taskId, file);
        return "redirect:/task/editTask/" + taskId;
    }

    @PostMapping("deleteFile/{taskId}")
    private String deleteFile(@PathVariable UUID taskId, @RequestParam UUID attachmentId) {
        attachmentContentService.deleteByAttachmentIdAndTaskId(attachmentId);
        return "redirect:/task/editTask/" + taskId;
    }
}

