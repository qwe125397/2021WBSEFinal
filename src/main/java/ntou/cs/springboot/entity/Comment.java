package ntou.cs.springboot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comment")
public class Comment {
	
	@Id
	private String id;
	
	@Indexed
	private String articleId;
	private String commentId;
	private String reviewrId;
	private String postTime;
	private String commentContent;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	public String getReviewrId() {
		return reviewrId;
	}

	public void setReviewrId(String reviewrId) {
		this.reviewrId = reviewrId;
	}
	
	public String getPostTime() {
		return postTime;
	}

	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	@Override
	public String toString() {
		return "Note [id=" + id + ", articleId=" + articleId + ", commentId=" + commentId + ", reviewrId=" + reviewrId +
				", postTime=" + postTime+ ", commentContent=" + commentContent + "]";
	}

}
