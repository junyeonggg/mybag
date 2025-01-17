package io.mybag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	// 홈 로그인 페이지
	@GetMapping("/")
	public String home() {
		return "redirect:/login";
	}
	// 홈 로그인 페이지
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	// 회원가입 페이지
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	// mybag 페이지
	@GetMapping("/mybag")
	public String mybag() {
		return "mybag";
	}
	
}
