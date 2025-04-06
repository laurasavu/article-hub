package com.science.demo.Controller;
import com.science.demo.Service.WriterService;
import com.science.demo.model.Writer;
import com.science.demo.model.Article;
import com.science.demo.Repoitories.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/writers")
public class WriterController {

    private final WriterService writerService;

    public WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

    // Create a new Writer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Writer createWriter(@RequestBody Writer writer) {
        return writerService.createWriter(writer);
    }

    // Get all Writers
    @GetMapping
    public List<Writer> getAllWriters() {
        return writerService.getAllWriters();
    }

    // Get a single Writer by ID
    @GetMapping("/{id}")
    public Writer getWriterById(@PathVariable Long id) {
        return writerService.getWriterById(id);
    }

    // Get all Articles for a specific Writer
    @GetMapping("/{id}/articles")
    public List<Article> getArticlesByWriter(@PathVariable Long id) {
        // Ensure the Writer exists
     return writerService.getArticlesByWriter(id);
    }

    // Update an existing Writer
    @PutMapping("/{id}")
    public Writer updateWriter(@PathVariable Long id, @RequestBody Writer updatedWriter) {
        // Fetch the existing Writer
       return writerService.updateWriter(id, updatedWriter);
    }

    // Delete a Writer
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWriter(@PathVariable Long id) {
        writerService.deleteWriter(id);
    }
}