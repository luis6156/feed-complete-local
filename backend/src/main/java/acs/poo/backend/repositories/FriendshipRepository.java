package acs.poo.backend.repositories;

import acs.poo.backend.entities.Friendship;
import acs.poo.backend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends CrudRepository<Friendship, Long> {
    Optional<Friendship> findBySenderAndReceiver(User sender, User receiver);
    List<Friendship> findBySenderOrReceiver(User sender, User receiver);
    List<Friendship> findBySenderOrReceiverAndIsAccepted(User sender, User receiver, boolean accepted);
}
