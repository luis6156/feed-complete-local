package acs.poo.backend.repositories;

import acs.poo.backend.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CommentRepository extends CrudRepository<Comment, String> {
}
