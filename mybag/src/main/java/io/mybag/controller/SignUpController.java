package io.mybag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import io.mybag.dto.UserDto;
import io.mybag.service.SignUpService;

@Controller
public class SignUpController {
	@Autowired
	private SignUpService signUpService;
	
	// 회원가입 페이지
	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute UserDto userDto) {
		signUpService.signup(userDto);
		
		return "redirect:/login";
	}
}
