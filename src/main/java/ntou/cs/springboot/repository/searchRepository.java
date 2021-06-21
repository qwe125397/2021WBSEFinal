package ntou.cs.springboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ntou.cs.springboot.entity.Article;

@Repository
public interface searchRepository extends MongoRepository<Article, String> {

	Article findFirstByArticleId(String articleId);
	
	List<Article> findAllByAuthorId(String authourId);

}