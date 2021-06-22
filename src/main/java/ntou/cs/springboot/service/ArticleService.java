package ntou.cs.springboot.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.entity.Favorite;
import ntou.cs.springboot.entity.Response;
import ntou.cs.springboot.entity.User;
import ntou.cs.springboot.repository.ArticleRepository;
import ntou.cs.springboot.repository.CommentRepository;
import ntou.cs.springboot.repository.FavoriteRepository;

@Service
public class ArticleService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;

	public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository ,FavoriteRepository favoriteRepository) {
		this.articleRepository = articleRepository;
		this.commentRepository = commentRepository;
		this.favoriteRepository = favoriteRepository;
	}
	
	public Article getArticle(String articleId) {
		return articleRepository.findFirstByArticleId(articleId);
	}
	
	public Integer getArtcleCount() {
		java.util.List<Article> articleList = articleRepository.findAll();
		Integer articleSize = articleList.size();
		return articleSize;
	}
	
	public String getSystemTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		return time;
	}
	
	public Article createArticle(Map<String, Object> map) {
		Integer count = getArtcleCount();
		count ++;
		String postTime = getSystemTime();
		
		Article article = new Article();
		article.setAuthorId(map.get("authorId").toString());
		article.setArticleId(count.toString());
		article.setArticleName(map.get("articleName").toString());
		article.setPostTime(postTime);
		article.setArticleContent(map.get("articleContent").toString());
		articleRepository.insert(article);
		
		return article;
	}

	public Response replaceArticle(Map<String, Object> map) {
		String postTime = getSystemTime();
		Article oldArticle = getArticle(map.get("articleId").toString());
		
		Article article = new Article();
		article.setId(oldArticle.getId());
		article.setAuthorId(map.get("authorId").toString());
		article.setArticleId(map.get("articleId").toString());
		article.setArticleName(map.get("articleName").toString());
		article.setPostTime(postTime);
		article.setArticleContent(map.get("articleContent").toString());
		articleRepository.save(article);
		
		Response response = new Response();
    	response.setCode(200);
    	response.setMsg("修改文章成功");

		return response;
	}

	public Response deleteArticle(String articleId) {
		articleRepository.deleteByArticleId(articleId);
		
		Response response = new Response();
    	response.setCode(200);
    	response.setMsg("刪除文章成功");
    	return response;
	}
	
	public Comment createComment(String articleId, Map<String, Object> map) {
		String postTime = getSystemTime();
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setReviewrId(map.get("reviewrId").toString());
		comment.setPostTime(postTime);
		comment.setCommentContent(map.get("commentContent").toString());
		
		Article oldarticle = getArticle(articleId);
		
		oldarticle.addArticleComment(comment);
		articleRepository.save(oldarticle);
		
		return commentRepository.insert(comment);
	}
	
	//查詢收藏列表
	public Favorite getFavorite(String userId) {
		return favoriteRepository.findFirstByUserId(userId);
	}
	
	//新增收藏
	public void addFavorite(String userId,String articleId) {
		Favorite favorite=favoriteRepository.findFirstByUserId(userId);
		ArrayList<String> favoriteList = favorite.getUserFavorite();
		Set<String> set = new HashSet<String>();
		set.addAll(favoriteList);
		set.add(articleId);
		ArrayList<String> favoriteList2 = new ArrayList<String>();
		favoriteList2.addAll(set);
		favorite.setUserFavorite(favoriteList2);
		favoriteRepository.save(favorite);
		
	}
	
	//移除收藏
	public void removeFavorite(String userId,String articleId) {
		Favorite favorite=favoriteRepository.findFirstByUserId(userId);
		ArrayList<String> favoriteList = favorite.getUserFavorite();
		for(int i=0;i<favoriteList.size();i++) {
			if(favoriteList.get(i).equals(articleId)) {
				favoriteList.remove(i);
			}
		}
		favorite.setUserFavorite(favoriteList);
		favoriteRepository.save(favorite);
		
	}

}
