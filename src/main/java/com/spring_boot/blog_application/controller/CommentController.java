package com.spring_boot.blog_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.spring_boot.blog_application.entity.Comment;
import com.spring_boot.blog_application.service.CommentService;
import java.util.Optional;

import java.util.List;

@RestController
// @CrossOrigin(origins = { "http://127.0.0.1:5500", "http://localhost:5500" })
@CrossOrigin(origins = { "https://rupali-personal-persuits.vercel.app" })
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Endpoint to add a comment
    @PostMapping("/create/{blogId}")
    public ResponseEntity<Comment> addComment(@PathVariable String blogId,
            @RequestBody Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Set the author of the comment to the logged-in user's username
        comment.setAuthor(userName);
        comment.setBlogId(blogId);

        // Call the service to add the comment
        Comment savedComment = commentService.addComment(blogId, comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    // Endpoint to get comments by blog ID
    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<Comment>> getCommentsByBlogId(@PathVariable String blogId) {
        List<Comment> comments = commentService.getCommentsByBlogId(blogId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no comments found
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Endpoint to edit a comment
    @PutMapping("/edit/{commentId}")
    // public ResponseEntity<Comment> editComment(@PathVariable String commentId,
    // @RequestBody Comment updatedComment) {
    // // Ensure the user is authenticated
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();
    // if (authentication == null || !authentication.isAuthenticated()) {
    // return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    // }

    // Comment editedComment = commentService.editComment(commentId,
    // updatedComment);
    // return new ResponseEntity<>(editedComment, HttpStatus.OK);
    // }

    public ResponseEntity<Comment> editComment(@PathVariable String commentId,
            @RequestBody Comment updatedComment) {
        // Ensure the user is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        // Fetch the comment to check ownership
        Optional<Comment> existingCommentOpt = commentService.findById(commentId);

        if (existingCommentOpt.isPresent()) {
            Comment existingComment = existingCommentOpt.get();

            // Check if the authenticated user is the owner of the comment
            if (!existingComment.getAuthor().equals(userName)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // User is not authorized to edit this comment
            }

            // Update the comment with new content
            existingComment.setContent(updatedComment.getContent());

            // Save the updated comment
            Comment editedComment = commentService.save(existingComment);
            return new ResponseEntity<>(editedComment, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Comment not found
    }

    // Endpoint to delete a comment
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.NO_CONTENT);
    }
}
