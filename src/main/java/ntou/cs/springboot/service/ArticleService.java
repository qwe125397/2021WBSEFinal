package ntou.cs.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.repository.ArticleRepository;
import ntou.cs.springboot.repository.CommentRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;

	public ArticleService(ArticleRepository repository) {
		this.articleRepository = repository;
	}
	
	public Article getArticleId(String articleId) {
		return articleRepository.findFirstByArticleId(articleId);
	}
	
	public Article createArticle(String authorId, String articleId, String articleName, String postTime, String articleContent) {
		Article article = new Article();
		article.setAuthorId(authorId);
		article.setArticleId(articleId);
		article.setArticleName(articleName);
		article.setPostTime(postTime);
		article.setArticleContent(articleContent);
		
		return articleRepository.insert(article);
	}

	public Article replaceArticle(String articleId, String authorId, String articleName, String postTime, String articleContent) {
		Article oldArticleId = getArticleId(articleId);
		
		Article article = new Article();
		article.setId(oldArticleId.getId());
		article.setAuthorId(authorId);
		article.setArticleName(articleName);
		article.setPostTime(postTime);
		article.setArticleContent(articleContent);
		
		return articleRepository.save(article);
	}

	public void deleteArticle(String articleId) {
		
		articleRepository.deleteByArticleId(articleId);
	}
	
	public Comment createComment(String articleId, String commentId, String reviewrId, String postTime, String commentContent) {
		Comment comment = new Comment();
		comment.setArticleId(articleId);
		comment.setCommentId(commentId);
		comment.setReviewrId(reviewrId);
		comment.setPostTime(postTime);
		comment.setCommentContent(commentContent);
		
		return commentRepository.insert(comment);
	}

}
