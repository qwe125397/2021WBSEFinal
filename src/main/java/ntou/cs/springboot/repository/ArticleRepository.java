package ntou.cs.springboot.repository;

import ntou.cs.springboot.entity.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {

	Article findFirstByArticleId(String articleId);

	void deleteByArticleId(String articleId);

}
