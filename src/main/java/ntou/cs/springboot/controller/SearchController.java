package ntou.cs.springboot.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ntou.cs.springboot.entity.Article;
import ntou.cs.springboot.entity.user;
import ntou.cs.springboot.service.searchService;
@RestController
@CrossOrigin(origins = "http://localhost:8888")
public class SearchController {
	@Autowired
    private searchService searchService;
	@GetMapping("/searchNewArticles")
	public List<Article> searchNewArticles() {
		List<Article> result=searchService.searchArticles();
		Collections.sort(result, new Comparator() {
			public int compare(Object a, Object b) {
			String one = ((Article)a).getPostTime();
			String two = ((Article)b).getPostTime();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			int big=0;
			try {
				if(dateFormatter.parse(one).before(dateFormatter.parse(one))) {
					big=-1;
				} else
					try {
						if(dateFormatter.parse(one).after(dateFormatter.parse(one))) {
							big=1;
						}
						else {
							
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return big ;
			}
			});
		return result;
	}
	@GetMapping("/searchHotArticles")
	public List<Article> searchHotArticles() {
		List<Article> result=searchService.searchArticles();
		return result;
	}
}
