package ntou.cs.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ntou.cs.springboot.entity.user;

@Repository
public interface userRepository extends MongoRepository<user, String> {
	user findFirstByuserId(String userId);
}