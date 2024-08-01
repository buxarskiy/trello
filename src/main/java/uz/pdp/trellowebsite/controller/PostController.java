package uz.pdp.trellowebsite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.trellowebsite.entity.Post;
import uz.pdp.trellowebsite.service.PostService;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("addPostPage")
    public String addPostPage() {
        return "addPost";
    }

    @PostMapping("createPost")
    private String addPost(@ModelAttribute Post post) {
       /* if (post.getIndex() == post.getIndex()) {
        }
        post.setIndex(1);*/
        postService.save(post);
        return "redirect:/";
    }

    @PostMapping("deletePost/{postId}")
    private String deletePost(@PathVariable UUID postId) {
        postService.delete(postId);
        return "redirect:/";
    }

    @GetMapping("editPostPage/{postId}")
    private String editPage(@PathVariable UUID postId, Model model) {
        Optional<Post> postOptional = postService.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            model.addAttribute("post", post);
            return "editPost";
        }
        return "redirect:/";
    }

    @PostMapping("update/{postId}")
    public String update(@RequestParam String name, @PathVariable UUID postId) {

        Optional<Post> postOptional = postService.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setName(name);
            postService.save(post);
        }
        return "redirect:/";
    }

    @GetMapping("change")
    public String changeNext(@RequestParam String postId, @RequestParam int index) {
        UUID postUUID = UUID.fromString(postId);
        postService.change(postUUID, index);
        return "redirect:/";
    }

}
