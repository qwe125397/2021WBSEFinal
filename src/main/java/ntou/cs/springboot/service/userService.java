package ntou.cs.springboot.service;

import java.util.Map;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.entity.user;
import ntou.cs.springboot.repository.userRepository;

@Service
public class userService {
	
	@Autowired
	private userRepository userRepository;
	

	public userService(userRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public user creatUser(Map<String, String> map) {
		user newuser = new user();
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
		newuser.setId(map.get("userId").toString());
		newuser.setPassword(map.get("password").toString());
		userRepository.insert(newuser);	
		return newuser;
	}
	public user getUser(String userId) {
		return userRepository.findFirstByuserId(userId);
	}

}
