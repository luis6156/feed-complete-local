package acs.poo.backend.controllers;

import acs.poo.backend.dtos.LikeDTO;
import acs.poo.backend.errors.PostNotFoundError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.services.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> likePost(@RequestBody @Valid LikeDTO like)
            throws UserNotFoundError, PostNotFoundError {
        likeService.addLikeToPost(like.getPostId(), like.getUserId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikePost(@RequestBody @Valid LikeDTO like)
            throws UserNotFoundError, PostNotFoundError {
        likeService.removeLikeFromPost(like.getPostId(), like.getUserId());
        return ResponseEntity.ok().build();
    }
}
