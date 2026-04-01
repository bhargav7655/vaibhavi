package com.vaibhavi.vaibhavi.service;

import com.vaibhavi.vaibhavi.entity.Comment;
import com.vaibhavi.vaibhavi.entity.User;
import com.vaibhavi.vaibhavi.repository.CommentRepository;
import com.vaibhavi.vaibhavi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository; // ✅ NEW

    // ✅ UPDATED CONSTRUCTOR
    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    // ✅ UPDATED METHOD
    public Comment addComment(Comment comment) {

        Long userId = comment.getUser().getId();

        // fetch user from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // attach user to comment
        comment.setUser(user);

        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment likeComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setLikes(comment.getLikes() + 1);
        return commentRepository.save(comment);
    }

    public Comment unlikeComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (comment.getLikes() > 0) {
            comment.setLikes(comment.getLikes() - 1);
        }

        return commentRepository.save(comment);
    }
}