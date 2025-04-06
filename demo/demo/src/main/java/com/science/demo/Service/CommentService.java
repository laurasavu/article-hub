package com.science.demo.Service;
import com.science.demo.Exceptions.ArticleNotFoundException;
import com.science.demo.Exceptions.CommentNotFoundEXception;
import com.science.demo.model.Comment;
import com.science.demo.model.Article;
import com.science.demo.Repoitories.CommentRepository;
import com.science.demo.Repoitories.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    // Create a new comment
    public Comment createComment(Comment comment) {
        // Ensure the Article exists
        Article article = articleRepository.findById(comment.getArticle().getId())
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " +
                        comment.getArticle().getId()));
        comment.setArticle(article);
        return commentRepository.save(comment);
    }

    // Get all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Get a comment by ID
    public Comment getCommentById(Long id) {
    return findCommentById(id);
    }

    // Update an existing comment
    public Comment updateComment(Long id, Comment updatedComment) {
        Comment existingComment = findCommentById(id);
        existingComment.setContent(updatedComment.getContent());

        if (updatedComment.getArticle() != null) {
            Article article = articleRepository.findById(updatedComment.getArticle().getId())
                    .orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + updatedComment.getArticle().getId()));
            existingComment.setArticle(article);
        }
        return commentRepository.save(existingComment);
    }

    private Comment findCommentById(Long id) {
       return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundEXception("Comment not found with ID: " + id));
    }

    // Delete a comment
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}