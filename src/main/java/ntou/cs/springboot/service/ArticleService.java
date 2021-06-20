package ntou.cs.springboot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.repository.ArticleRepository;
import ntou.cs.springboot.repository.CommentRepository;

@Service
public class ArticleService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
		this.articleRepository = articleRepository;
		this.commentRepository = commentRepository;
	}
	
	public Article getArticle(String articleId) {
		return articleRepository.findFirstByArticleId(articleId);
	}
	
	public Article createArticle(Map<String, Object> map) {
		Article article = new Article();
		article.setAuthorId(map.get("authorId").toString());
		article.setArticleId(map.get("articleId").toString());
		article.setArticleName(map.get("articleName").toString());
		article.setPostTime(map.get("postTime").toString());
		article.setArticleContent(map.get("articleContent").toString());
		
		return articleRepository.insert(article);
	}

	public Article replaceArticle(Map<String, Object> map) {
//		String articleId, String authorId, String articleName, String postTime, String articleContent
		Article oldArticle = getArticle(map.get("articleId").toString());
		
		Article article = new Article();
		article.setId(oldArticle.getId());
		article.setAuthorId(map.get("authorId").toString());
		article.setArticleId(map.get("articleId").toString());
		article.setArticleName(map.get("articleName").toString());
		article.setPostTime(map.get("postTime").toString());
		article.setArticleContent(map.get("articleContent").toString());

		return articleRepository.save(article);
	}

	public void deleteArticle(String articleId) {
		
		articleRepository.deleteByArticleId(articleId);
	}
	
	public Comment createComment(String articleId, Map<String, Object> map) {
//		String articleId, String commentId, String reviewrId, String postTime, String commentContent
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setCommentId(map.get("commentId").toString());
		comment.setReviewrId(map.get("reviewrId").toString());
		comment.setPostTime(map.get("postTime").toString());
		comment.setCommentContent(map.get("commentContent").toString());
		
		Article oldarticle = getArticle(articleId);
		
		Article article = new Article();
		article.setId(oldarticle.getId());
		article.setAuthorId(oldarticle.getAuthorId());
		article.setArticleId(oldarticle.getArticleId());
		article.setArticleName(oldarticle.getArticleName());
		article.setArticleContent(oldarticle.getArticleContent());
		article.setArticleComment(comment);
		articleRepository.save(article);
		
		return commentRepository.insert(comment);
	}

}
