package ntou.cs.springboot.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ntou.cs.springboot.entity.FavRequest;
import ntou.cs.springboot.service.ArticleService;

@Component
public class ReceiveMessageListener {

	@Autowired
    private ArticleService articleService;
	
	//監聽Queue中是否有資料，若有資料則進行消費。
	@RabbitListener(queues={"favorite.queue"})
    public void receive(FavRequest favRequest) {
		articleService.addFavorite(favRequest.getUserId(), favRequest.getArticleId());
		System.out.println("favorite.queue收到");
    }
}
