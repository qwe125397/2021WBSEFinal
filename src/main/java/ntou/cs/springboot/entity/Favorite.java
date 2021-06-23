package ntou.cs.springboot.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="favorite")
public class Favorite {
	
	
	public String userId;
	public ArrayList<String> userFavorite;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public ArrayList<String> getUserFavorite() {
		return userFavorite;
	}
	public void setUserFavorite(ArrayList<String> userFavorite) {
		this.userFavorite = userFavorite;
	}
	
	
}
