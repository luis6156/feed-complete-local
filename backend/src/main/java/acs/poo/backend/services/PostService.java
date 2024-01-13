package acs.poo.backend.services;

import acs.poo.backend.dtos.CommentDTO;
import acs.poo.backend.dtos.PostDTO;
import acs.poo.backend.entities.Comment;
import acs.poo.backend.entities.Post;
import acs.poo.backend.errors.PostNotFoundError;
import acs.poo.backend.errors.UserNotFoundError;
import acs.poo.backend.repositories.CommentRepository;
import acs.poo.backend.repositories.FriendshipRepository;
import acs.poo.backend.repositories.PostRepository;
import acs.poo.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public void createPost(PostDTO postDTO, String uid) throws UserNotFoundError {
        var post = modelMapper.map(postDTO, Post.class);
        post.setCreatedAt(Timestamp.from(Instant.now()));
        post.setUser(userRepository.findById(uid).orElseThrow(UserNotFoundError::new));
        postRepository.save(post);
    }

    public List<Post> listPosts() {
        var posts = new ArrayList<>(StreamSupport.stream(postRepository.findAll().spliterator(), false).toList());
        Collections.shuffle(posts);
        return posts;
    }

    public List<Post> getFeedPosts(String uid) throws UserNotFoundError {
        var user = userRepository.findById(uid).orElseThrow(UserNotFoundError::new);

        var friendships = friendshipRepository.findBySenderOrReceiverAndIsAccepted(user, user, true);
        var users = friendships.stream()
                .map(friendship -> {
                    if (friendship.getSender().equals(user)) {
                        return friendship.getReceiver();
                    }
                    return friendship.getSender();
                }).toList();

        return postRepository.findAllByUserIn(users);
    }

    public void addCommentToPost(String userId, String postUid, CommentDTO commentDTO) throws PostNotFoundError, UserNotFoundError {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundError::new);
        var post = postRepository.findById(postUid).orElseThrow(PostNotFoundError::new);
        var comment = commentRepository.save(modelMapper.map(commentDTO, Comment.class));
        comment.setUser(user);
        comment.setCreatedAt(Timestamp.from(Instant.now()));
        post.getComments().add(comment);
        postRepository.save(post);
    }

    public void deletePost(String postUid) throws PostNotFoundError {
        var post = postRepository.findById(postUid).orElseThrow(PostNotFoundError::new);
        postRepository.delete(post);
    }

    public Post getPostById(String postUid) throws PostNotFoundError {
        return postRepository.findById(postUid).orElseThrow(PostNotFoundError::new);
    }

    public List<Post> getPostsByUserId(String userId) throws UserNotFoundError {
        return postRepository.findAllByUserIn(List.of(userRepository.findById(userId).orElseThrow(UserNotFoundError::new)));
    }
}
