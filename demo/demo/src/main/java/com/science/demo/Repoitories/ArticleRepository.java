package com.science.demo.Repoitories;

import com.science.demo.model.Article;
import com.science.demo.model.Writer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByWriter_Id(Long writerId);

    List<Article> findByWriter(Writer writer);
}

