package vttp.batch5.groupb.day36_workshop_blob.server.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.groupb.day36_workshop_blob.server.models.Post;
import vttp.batch5.groupb.day36_workshop_blob.server.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepo;
    
    public boolean addPost(Post post) {
        String postId = UUID.randomUUID().toString().substring(0, 8);
        post.setPostId(postId);
        return postRepo.addPost(post);
    }

    public List<Post> getAllPosts() {
        return postRepo.getAllPosts();
    }
}
