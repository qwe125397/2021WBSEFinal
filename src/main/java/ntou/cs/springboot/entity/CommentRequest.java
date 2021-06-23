package ntou.cs.springboot.entity;

public class CommentRequest {
	
	private String reviewrId;
	private String commentContent;
	
	public String getReviewrId() {
		return reviewrId;
	}
	public void setReviewrId(String reviewrId) {
		this.reviewrId = reviewrId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
}
