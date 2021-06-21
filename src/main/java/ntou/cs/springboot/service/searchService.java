package ntou.cs.springboot.service;

import java.util.Map;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.Comment;
import ntou.cs.springboot.entity.user;
import ntou.cs.springboot.repository.ArticleRepository;
import ntou.cs.springboot.repository.searchRepository;
import ntou.cs.springboot.repository.userRepository;

@Service
public class searchService {
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private searchRepository searchRepository;
	@Autowired
	private userRepository userRepository;
	
	public searchService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public user creatUser(Map<String, String> map) {
		user newuser1 = new user();
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
		newuser1.setId(map.get("userId").toString());
		newuser1.setPassword(map.get("password").toString());
		userRepository.insert(newuser1);	
		return newuser1;
	}
	public List<Article> searchArticles(){
		List<Article> resultArrayList= searchRepository.findAll();
		return resultArrayList;
	}
	public List<Article> searchCertifyArticles(String authourId){
		List<Article> resultArrayList= searchRepository.findAllByAuthorId(authourId);
		return resultArrayList;
	}
	

}
