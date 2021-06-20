package ntou.cs.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ntou.cs.springboot.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

	Comment findFirstByArticleId(String articleId);
}
