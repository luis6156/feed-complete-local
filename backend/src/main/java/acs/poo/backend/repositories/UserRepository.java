package acs.poo.backend.repositories;

import acs.poo.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
