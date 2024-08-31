package com.spring_boot.blog_application.service;

import com.spring_boot.blog_application.repo.CommentRepository;
import com.spring_boot.blog_application.entity.Comment;
import com.spring_boot.blog_application.entity.BlogEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository; // Your repository for comments

    @Autowired
    private BlogService blogService; // Inject BlogService to update the blog entry

    public Comment addComment(String blogId, Comment comment) {

        comment.setCreatedAt(LocalDateTime.now());
        // Save the comment
        comment.setBlogId(blogId); // Optionally set the blogId in the comment
        Comment savedComment = commentRepository.save(comment);

        // Update the BlogEntity
        BlogEntity blogEntity = blogService.blogById(blogId).orElseThrow(() -> new RuntimeException("Blog not found"));
        blogEntity.getComments().add(savedComment);
        blogService.saveEntry(blogEntity); // Save the updated blog entry

        return savedComment;
    }

    // Method to get comments by blog ID
    public List<Comment> getCommentsByBlogId(String blogId) {
        return commentRepository.findByBlogId(blogId);
    }

    // Method to edit a comment
    public Comment editComment(String commentId, Comment updatedComment) { // Change Long to String
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Update the comment content
        existingComment.setContent(updatedComment.getContent());
        existingComment.setUpdatedAt(LocalDateTime.now()); // Update the updatedAt timestamp

        return commentRepository.save(existingComment);
    }

    // Method to delete a comment
    public void deleteComment(String commentId) { // Change Long to String
        if (!commentRepository.existsById(commentId)) { // Change Long to String
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(commentId); // Change Long to String
    }

    // Find a comment by ID
    public Optional<Comment> findById(String commentId) {
        return commentRepository.findById(commentId);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment); // Assuming save is defined in the JPA repository
    }
}
