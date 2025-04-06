package com.science.demo.Service;
import com.science.demo.Exceptions.WriterNotFoundException;
import com.science.demo.model.Writer;
import com.science.demo.model.Article;
import com.science.demo.Repoitories.WriterRepository;
import com.science.demo.Repoitories.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WriterService {

    private final WriterRepository writerRepository;
    private final ArticleRepository articleRepository;

    public WriterService(WriterRepository writerRepository, ArticleRepository articleRepository) {
        this.writerRepository = writerRepository;
        this.articleRepository = articleRepository;
    }

    // Create a new writer
    public Writer createWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    // Get all writers
    public List<Writer> getAllWriters() {
        return writerRepository.findAll();
    }

    // Get writer by ID
    public Writer getWriterById(Long id) {
        return findWriterOrExp(id);
    }



    // Get all articles for a writer
    public List<Article> getArticlesByWriter(Long writerid) {
        Writer writer = findWriterOrExp(writerid);
        return articleRepository.findByWriter(writer);
    }

    // Update an existing writer
    public Writer updateWriter(Long id, Writer updatedWriter) {
        Writer existingWriter = writerRepository.findById(id)
                .orElseThrow(() -> new WriterNotFoundException("Writer not found with ID: " + id));

        // Update name and email
        existingWriter.setName(updatedWriter.getName());
        existingWriter.setEmail(updatedWriter.getEmail());

        // Save and return the updated Writer
        return writerRepository.save(existingWriter);
    }

    // Delete a writer
    public void deleteWriter(Long id) {
        writerRepository.deleteById(id);
    }

    private Writer findWriterOrExp(Long id) {
        return writerRepository.findById(id)
                .orElseThrow(() -> new WriterNotFoundException("Writer not found with ID: " + id));
    }
}