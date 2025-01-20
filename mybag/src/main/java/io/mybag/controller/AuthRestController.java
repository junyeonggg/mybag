package io.mybag.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mybag.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/login")
	public String jwtToken(@RequestBody Map<String,Object> request) {
		// db에서 비교하여 인증 확인
		//userService.loadUserByUsername(username,password);
		System.out.println("username : "+request.get("username"));
		String username = (String) request.get("username");
		return jwtTokenProvider.createToken(username);
	}
}
