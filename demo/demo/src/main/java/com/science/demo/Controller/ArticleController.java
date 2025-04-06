package com.science.demo.Controller;
import com.science.demo.Service.ArticleService;
import com.science.demo.model.Article;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
   

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArt(article);
    }
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }


    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle) {

        return articleService.save(id,updatedArticle);
    }

    // 5. Delete an Article
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
    }

}

