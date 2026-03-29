package com.vaibhavi.vaibhavi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    // Constructors
    public Comment() {}

    public Comment(String text) {
        this.text = text;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // For debugging
    @Override
    public String toString() {
        return "Comment{id=" + id + ", text='" + text + "'}";
    }
}