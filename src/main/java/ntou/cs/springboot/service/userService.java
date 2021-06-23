package ntou.cs.springboot.service;

import java.util.Map;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.entity.Favorite;
import ntou.cs.springboot.entity.user;
import ntou.cs.springboot.repository.FavoriteRepository;
import ntou.cs.springboot.repository.userRepository;

@Service
public class userService {
	
	@Autowired
	private userRepository userRepository;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	

	public userService(userRepository userRepository,FavoriteRepository favoriteRepository) {
		this.userRepository = userRepository;
		this.favoriteRepository = favoriteRepository;
	}
	
	public user creatUser(Map<String, String> map) {
		user newuser = new user();
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
		newuser.setId(map.get("account").toString());
		newuser.setPassword(map.get("password").toString());
		userRepository.insert(newuser);	
		
		//在資料庫中新增favorite
		Favorite favorite = new Favorite();
		favorite.setUserId(map.get("account").toString());
		ArrayList<String> favList = new ArrayList<>();
		favorite.setUserFavorite(favList);
		favoriteRepository.insert(favorite);
		
		return newuser;
	}
	public user getUser(String userId) {
		return userRepository.findFirstByuserId(userId);
	}

}
