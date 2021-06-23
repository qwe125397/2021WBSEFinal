package ntou.cs.springboot.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "article")
public class Article{
	
	@Id
	private String id;
	
	@Indexed
	public String authorId;
	public String articleId;
	public String articleName;
	public String postTime;
	public String articleContent;
	public ArrayList<Comment> articleComment = new ArrayList(); 
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	public String getArticleName() {
		return articleName;
	}
	
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	
	public String getPostTime() {
		return postTime;
	}
	
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
	public String getArticleContent() {
		return articleContent;
	}
	
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	
	public ArrayList<Comment> getArticleComment() {
		return articleComment;
	}
	
	public void addArticleComment(Comment articleComment) {
		this.articleComment.add(articleComment);
	}
	
	@Override
	public String toString() {
		return "Note [id=" + id + ", authorId=" + authorId + ", articleId=" + articleId +
				", articleName=" + articleName+ ", postTime=" + postTime+ ", articleContent=" +
				articleContent + ", articleComment=" + articleComment+ "]";
	}

}