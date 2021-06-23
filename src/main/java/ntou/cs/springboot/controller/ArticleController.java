package ntou.cs.springboot.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.entity.FavRequest;
import ntou.cs.springboot.entity.Favorite;
import ntou.cs.springboot.entity.Response;
import ntou.cs.springboot.entity.User;
import ntou.cs.springboot.listener.ReceiveMessageListener;
import ntou.cs.springboot.service.ArticleService;


@RestController
public class ArticleController {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ReceiveMessageListener receiveMessageListener;
	
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
    
    //查看收藏列表
    @ApiOperation(value="查看收藏列表",notes="已陣列形式查看該使用者所收藏的文章")
    @GetMapping(value= "/favorite/{userId}")
    public ResponseEntity<ArrayList<String>> getFavorite(@ApiParam(required=true,value="url內放入要查詢的userId")@PathVariable("userId") String userId){
    	
    	Favorite favorite = articleService.getFavorite(userId);
    	
    	return ResponseEntity.ok(favorite.getUserFavorite());
    }
    
    //新增收藏，經過rabbitmq
    @ApiOperation(value="新增收藏",notes="請傳入FavRequest格式")
    @PostMapping(value="/newFavorite")
    public ResponseEntity<Response> addFavorite(@ApiParam(required=true,value="傳入userId與articleId")@RequestBody FavRequest favRequest){
    	rabbitTemplate.convertAndSend("favorite.queue", favRequest);
    	
    	Response response = new Response();
    	response.setCode(201);
    	response.setMsg("新增收藏成功");
    	
    	return ResponseEntity.ok(response);
    }
    
    //移除收藏
    @ApiOperation(value="移除收藏",notes="請傳入FavRequest格式")
    @PostMapping(value="/removeFavorite")
    public ResponseEntity<Response> removeFavorite(@ApiParam(required=true,value="傳入userId與articleId")@RequestBody FavRequest favRequest){
    	articleService.removeFavorite(favRequest.getUserId(), favRequest.getArticleId());
    	Response response = new Response();
    	response.setCode(201);
    	response.setMsg("移除收藏成功");
    	
    	return ResponseEntity.ok(response);
    }
    
    

}
