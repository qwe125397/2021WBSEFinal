package ntou.cs.springboot.controller;
import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ntou.cs.springboot.entity.Response;
import ntou.cs.springboot.entity.user;
import ntou.cs.springboot.service.userService;
@RestController
@CrossOrigin(origins = "http://localhost:8888")
public class loginController {
	@Autowired
    private userService userService;
	@PostMapping("/signIn")
	public ResponseEntity<Response> login(@RequestBody Map<String, String> map) {
		user checkUser=userService.getUser(map.get("account").toString());
		Response response = new Response();
		URI location = null;
		System.out.println(checkUser.getPassword());
		if(checkUser.getPassword().equals(map.get("password").toString())){
			response.setCode(201);
	    	response.setMsg("登入成功");
		}
		else {
	    	response.setCode(201);
	    	response.setMsg("帳號或密碼錯誤");
		}
		return ResponseEntity.created(location).body(response);
	}
	@PostMapping("/signUp")
	public ResponseEntity<Response> signUp(@RequestBody Map<String, String> map) {
		Response response = new Response();
		URI location=null;
		if(userService.getUser(map.get("account").toString()) != null) {
			response.setCode(201);
	    	response.setMsg("帳號已存在");
		}
		else {
			System.out.println(map.get("account"));
			user newUser = userService.creatUser(map);
			location = ServletUriComponentsBuilder
	                .fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(newUser.getId())
	                .toUri();
			response.setCode(201);
	    	response.setMsg("註冊成功");
		}
		return ResponseEntity.created(location).body(response);
	}
}
