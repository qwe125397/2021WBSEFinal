package ntou.cs.springboot.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.service.ArticleService;


@RestController
public class ArticleController {
	
	@Autowired
    private ArticleService articleService;
	
	@PostMapping(value = "/newArticle")
    public ResponseEntity<Article> createNote(@RequestBody String authorId, String articleId, String articleName, String postTime, String articleContent) {
		Article article = articleService.createArticle(authorId, articleId, articleName, postTime, articleContent);
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(article.getId())
                .toUri();

        return ResponseEntity.created(location).body(article);
    }

    @PutMapping(value = "/editArticle")
    public ResponseEntity<Article> replaceNote(@RequestBody String authorId, String articleId, String articleName, String postTime, String articleContent) {
    	Article note = articleService.replaceArticle(authorId, articleId, articleName, postTime, articleContent);
    	
        return ResponseEntity.ok(note);
    }

    @DeleteMapping(value = "/deleteArticle")
    public ResponseEntity<Article> deleteNote(@RequestBody String articleId) {
    	articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }
    
    

}
