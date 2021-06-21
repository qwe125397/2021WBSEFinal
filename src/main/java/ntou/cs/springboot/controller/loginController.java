package ntou.cs.springboot.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


import ntou.cs.springboot.entity.user;
import ntou.cs.springboot.service.userService;
@RestController
@CrossOrigin(origins = "http://localhost:8888")
public class loginController {
	@Autowired
    private userService userService;
	@PostMapping("/signIn")
	public JSONObject login(@RequestBody Map<String, String> map) {
		user checkUser=userService.getUser(map.get("account").toString());
		JSONObject ack;
		if(checkUser.getPassword()==(map.get("password").toString())){
			ack=new JSONObject( "{\"code\":200,\"message\":\"登入成功\"}");
		}
		else {
			ack=new JSONObject( "{\"code\":400,\"message\":\"帳號或密碼錯誤\"}");
		}
		return ack;
	}
	@PostMapping("/signUp")
	public JSONObject signUp(@RequestBody Map<String, String> map) {
		JSONObject ack;
		System.out.println(map.get("account"));
		if(userService.getUser(map.get("account").toString()) != null) {
			ack=new JSONObject( "{\"code\":400,\"message\":\"該使用者已存在\"}");
		}
		else {
			user newUser = userService.creatUser(map);
			ack=new JSONObject( "{\"code\":200,\"message\":\"成功註冊\"}");
		}
		return ack;
	}
}
