package com.science.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "Article is required")
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @NotNull(message = "Writer is required")
    @ManyToOne
    @JoinColumn(name = "writer", nullable = false
    )
    private Writer writer;
}