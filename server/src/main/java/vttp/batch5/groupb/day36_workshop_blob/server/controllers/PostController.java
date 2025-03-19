package vttp.batch5.groupb.day36_workshop_blob.server.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.groupb.day36_workshop_blob.server.models.Post;
import vttp.batch5.groupb.day36_workshop_blob.server.services.PostService;

@RestController
@RequestMapping(path="/api/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    @Autowired
    private PostService postSvc;
    
    @PostMapping(consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> savePost(
        @RequestPart MultipartFile picture,
        @RequestPart String comments
    ) throws IOException {
        Post post = new Post();
        post.setPicture(picture.getInputStream());
        post.setComments(comments);

        postSvc.addPost(post);

        JsonObject payload = Json.createObjectBuilder().add("message", "Successfully added to database").build();
        return ResponseEntity.ok().body(payload.toString());
    }

    @GetMapping
    public ResponseEntity<String> getAllPosts() {
        List<Post> posts = postSvc.getAllPosts();

        JsonArrayBuilder ab = Json.createArrayBuilder();

        posts.forEach(post -> ab.add(post.toJson()));

        return ResponseEntity.ok().body(ab.build().toString());
    }
}
