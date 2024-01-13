package acs.poo.backend.controllers;

import acs.poo.backend.dtos.CommentDTO;
import acs.poo.backend.dtos.PostDTO;
import acs.poo.backend.entities.Post;
import acs.poo.backend.errors.PostNotFoundError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostDTO postDTO, @RequestParam String userId) throws UserNotFoundError {
        postService.createPost(postDTO, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> listPosts() {
        return ResponseEntity.ok(postService.listPosts());
    }

    @GetMapping("/following")
    public ResponseEntity<List<Post>> listPostsOfFriends(@RequestParam String userId) throws UserNotFoundError {
        return ResponseEntity.ok(postService.getFeedPosts(userId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePost(@RequestParam String uid) throws PostNotFoundError {
        postService.deletePost(uid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/comment")
    public ResponseEntity<Void> addCommentToPost(@RequestParam String userId, @RequestParam String postId, @RequestBody CommentDTO commentDTO)
            throws PostNotFoundError, UserNotFoundError {
        postService.addCommentToPost(userId, postId, commentDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public ResponseEntity<Post> getPostById(@RequestParam String postId) throws PostNotFoundError {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable String userId) throws UserNotFoundError {
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }
}
