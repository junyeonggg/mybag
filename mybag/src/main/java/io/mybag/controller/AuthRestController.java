package io.mybag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.mybag.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/login")
	public String jwtToken(@RequestParam("username")String username,@RequestParam("password") String password) {
		// db에서 비교하여 인증 확인
		//userService.loadUserByUsername(username,password);
		return jwtTokenProvider.createToken(username);
	}
}
