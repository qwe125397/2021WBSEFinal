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
import ntou.cs.springboot.entity.Response;
import ntou.cs.springboot.service.ArticleService;


@RestController
public class ArticleController {
	
	@Autowired
    private ArticleService articleService;
	
	@PostMapping(value = "/newArticle")
    public ResponseEntity<Response> createArticle(@RequestBody Map<String, Object> map) {
		Article article = articleService.createArticle(map);
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(article.getId())
                .toUri();
    	
    	Response response = new Response();
    	response.setCode(201);
    	response.setMsg("新增文章成功");

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping(value = "/editArticle")
    public ResponseEntity<Response> replaceArticle(@RequestBody Map<String, Object> map) {
    	Response response = articleService.replaceArticle(map);
    	
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/deleteArticle/{articleId}")
    public ResponseEntity<Response> deleteArticle(@PathVariable("articleId") String articleId) {
    	Response response = articleService.deleteArticle(articleId);
    	
        return ResponseEntity.ok(response);
    }
    
    @PostMapping(value = "/{articleId}/newComment")
    public ResponseEntity<Response> createComment(@PathVariable("articleId") String articleId, @RequestBody Map<String, Object> map) {
    	Comment comment = articleService.createComment(articleId, map);
    	
    	URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(comment.getId())
                .toUri();
    	
    	Response response = new Response();
    	response.setCode(201);
    	response.setMsg("新增文章成功");

        return ResponseEntity.created(location).body(response);
    }

}
