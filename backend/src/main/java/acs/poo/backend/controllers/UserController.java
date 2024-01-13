package acs.poo.backend.controllers;

import acs.poo.backend.entities.User;
import acs.poo.backend.errors.UserAlreadyExistsError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) throws UserAlreadyExistsError {
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateUser(@RequestBody User user) throws UserNotFoundError {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{uid}")
    public ResponseEntity<User> getUser(@PathVariable String uid) throws UserNotFoundError {
        return ResponseEntity.ok(userService.getUserById(uid));
    }
}
