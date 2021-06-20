package ntou.cs.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ntou.cs.springboot.entity.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

	Comment findFirstByArticleId(String articleId);
}
