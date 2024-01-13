package acs.poo.backend.repositories;

import acs.poo.backend.entities.Like;
import acs.poo.backend.entities.Post;
import acs.poo.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
}
