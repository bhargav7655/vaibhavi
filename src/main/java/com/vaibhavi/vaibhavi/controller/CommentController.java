package com.vaibhavi.vaibhavi.controller;

import com.vaibhavi.vaibhavi.entity.Comment;
import com.vaibhavi.vaibhavi.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        System.out.println("Received comment: " + comment);  // Debugging
        return commentService.addComment(comment);
    }

    @GetMapping
    public List<Comment> getComments() {
        return commentService.getAllComments();
    }
}