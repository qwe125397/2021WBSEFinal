package ntou.cs.springboot.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "user")
public class user {
	@Id
	private String id;
	@Indexed
	private String userId;
	private String password;
	private ArrayList userArticle; 
	public String getId() {
		return userId;
	}

	public void setId(String id) {
		this.userId = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void addUserArticle(String articleId) {
		this.userArticle.add(articleId);
	}
	public void deleteUserArticle(String articleId) {
		this.userArticle.remove(articleId);
	}
	public ArrayList getUserArticle() {
		return userArticle;
	}
}
