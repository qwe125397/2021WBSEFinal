package ntou.cs.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ntou.cs.springboot.entity.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

	Article findFirstByArticleId(String articleId);

	void deleteByArticleId(String articleId);

}
