package acs.poo.backend.services;

import acs.poo.backend.entities.User;
import acs.poo.backend.errors.UserAlreadyExistsError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user) throws UserAlreadyExistsError {
        if (userRepository.existsById(user.getUid())) {
            throw new UserAlreadyExistsError();
        }

        userRepository.save(user);
    }

    public void updateUser(User user) throws UserNotFoundError {
        if (!userRepository.existsById(user.getUid())) {
            throw new UserNotFoundError();
        }
    }

    public List<User> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    public User getUserById(String uid) throws UserNotFoundError {
        return userRepository.findById(uid).orElseThrow(UserNotFoundError::new);
    }
}
