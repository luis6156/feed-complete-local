package acs.poo.backend.controllers;

import acs.poo.backend.errors.FriendshipAlreadyExistsError;
import acs.poo.backend.errors.FriendshipNotFoundError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friendships")
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    @PostMapping
    public ResponseEntity<Void> createFriendship(@RequestParam String sender, @RequestParam String receiver)
            throws UserNotFoundError, FriendshipAlreadyExistsError {
        friendshipService.createFriendship(sender, receiver);
        return ResponseEntity.ok().build();
    }

    @PatchMapping()
    public ResponseEntity<Void> acceptFriendship(@RequestParam String sender, @RequestParam String receiver)
            throws FriendshipNotFoundError, UserNotFoundError {
        friendshipService.acceptFriendship(sender, receiver);
        return ResponseEntity.ok().build();
    }
}
