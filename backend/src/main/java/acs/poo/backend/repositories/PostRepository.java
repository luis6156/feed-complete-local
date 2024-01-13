package acs.poo.backend.repositories;

import acs.poo.backend.entities.Post;
import acs.poo.backend.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, String> {
    List<Post> findAllByUserIn(Collection<User> users);
}
