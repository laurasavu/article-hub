package com.science.demo.Service;
import com.science.demo.Exceptions.ArticleNotFoundException;
import com.science.demo.Exceptions.WriterNotFoundException;
import com.science.demo.Repoitories.ArticleRepository;
import com.science.demo.Repoitories.WriterRepository;
import com.science.demo.model.Article;
import com.science.demo.model.Writer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final WriterRepository writerRepository;


    public ArticleService(ArticleRepository articleRepository, WriterRepository writerRepository) {
        this.articleRepository = articleRepository;
        this.writerRepository = writerRepository;

    }
        public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

        public Article createArt(@Valid Article article){
            Writer writer = getWriter(article);
            article.setWriter(writer);
                return articleRepository.save(article);
            }

    private Writer getWriter(Article article) {

        return writerRepository.findById(article.getWriter().getId())
                .orElseThrow(() -> new WriterNotFoundException("Writer not found with ID: " +
                        article.getWriter().getId()));
    }


    public Article getArticleById(Long id){
            return findArticleByIdOrThrow(id);
        }

    //TODO
        /*  
        public List<Article> getArticlesByWriter(Long writerId){
            Writer writer = writerRepository.findById(writerId)
                    .orElseThrow(() -> new WriterNotFoundException("Writer not found with ID: " + writerId));
         return articleRepository.findByWriter(writer);}
        */
    
        public Article save(Long id,Article updatedArticle){

            Article existingArticle = findArticleByIdOrThrow(id);

            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setContent(updatedArticle.getContent());

            if (updatedArticle.getWriter() != null) {
                Writer writer = writerRepository.findById(updatedArticle.getWriter().getId())
                        .orElseThrow(() ->  new WriterNotFoundException("Writer not found with ID: " 
                                + updatedArticle.getWriter().getId()));
                existingArticle.setWriter(writer);
            }
            return articleRepository.save(existingArticle);
        }

        public void deleteArticleById(Long id) {
            articleRepository.deleteById(id);
        }

        private Article findArticleByIdOrThrow(Long id) {return articleRepository.findById(id)
            .orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + id));
    // helper method,does not become part of the publicly exposed API of the service
    }

    }