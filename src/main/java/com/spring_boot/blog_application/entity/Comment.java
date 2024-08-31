package com.spring_boot.blog_application.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "comments")
@Data
public class Comment {
    @Id
    private String id;
    private String content;
    private String author;
    private String blogId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default constructor
    public Comment() {
        // Automatically set createdAt and updatedAt to the current time
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Constructor with parameters
    public Comment(String content, String author, String blogId) {
        this.content = content;
        this.author = author;
        this.blogId = blogId;
        this.createdAt = LocalDateTime.now(); // Set createdAt to the current time
        this.updatedAt = LocalDateTime.now(); // Set updatedAt to the current time
    }

    // Method to update the comment content and refresh the updatedAt timestamp
    public void updateContent(String newContent) {
        this.content = newContent;
        this.updatedAt = LocalDateTime.now(); // Update the updatedAt timestamp
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now(); // Update updatedAt when content is set
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
