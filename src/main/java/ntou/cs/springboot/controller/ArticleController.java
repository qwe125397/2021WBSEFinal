package ntou.cs.springboot.controller;

import java.net.URI;
import java.util.Map;

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
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.service.ArticleService;


@RestController
public class ArticleController {
	
	@Autowired
    private ArticleService articleService;
	
	@PostMapping(value = "/newArticle")
    public ResponseEntity<Article> createArticle(@RequestBody Map<String, Object> map) {
		Article article = articleService.createArticle(map);
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(article.getId())
                .toUri();

        return ResponseEntity.created(location).body(article);
    }

    @PutMapping(value = "/editArticle")
    public ResponseEntity<Article> replaceArticle(@RequestBody Map<String, Object> map) {
    	Article article = articleService.replaceArticle(map);
    	
        return ResponseEntity.ok(article);
    }

    @DeleteMapping(value = "/deleteArticle")
    public ResponseEntity<Article> deleteArticle(@RequestBody String articleId) {
    	articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping(value = "/{articleId}/newComment")
    public ResponseEntity<Comment> createComment(@PathVariable("articleId") String articleId, @RequestBody Map<String, Object> map) {
    	Comment comment = articleService.createComment(articleId, map);
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(comment.getId())
                .toUri();

        return ResponseEntity.created(location).body(comment);
    }

}
