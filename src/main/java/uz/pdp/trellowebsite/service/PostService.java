package uz.pdp.trellowebsite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.trellowebsite.entity.Post;
import uz.pdp.trellowebsite.repo.PostRepo;
import uz.pdp.trellowebsite.service.base.BaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService implements BaseService<Post, UUID> {
    private final PostRepo postRepo;

    @Override
    public void save(Post post) {
        postRepo.save(post);
    }

    @Override
    public void delete(UUID uuid) {
        postRepo.deleteById(uuid);
    }

    @Override
    public void update(Post post, UUID uuid) {

    }

    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    public List<Post> findAllByOrderId() {
        return postRepo.findAllByOrderById();
    }


    public Optional<Post> findById(UUID id) {
        return postRepo.findById(id);
    }


    public void change(UUID postId, int index) {
        Optional<Post> optionalPost = postRepo.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            List<Post> posts = postRepo.findAll();
            posts.remove(post);

            posts.add(index, post);

            for (int i = 0; i < posts.size(); i++) {
                posts.get(i).setIndex(i);
                postRepo.save(posts.get(i));
            }
        }
    }

   /* public List<Post> findAllByUser(UUID id) {
        return postRepo.findAllTaskAmount(id);
    }*/
}
