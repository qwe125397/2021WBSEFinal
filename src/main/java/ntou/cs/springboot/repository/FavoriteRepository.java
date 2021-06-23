package ntou.cs.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ntou.cs.springboot.entity.Favorite;
import ntou.cs.springboot.entity.User;

@Repository
public interface FavoriteRepository extends MongoRepository<Favorite, String> {

	Favorite findFirstByUserId(String userId);
}
