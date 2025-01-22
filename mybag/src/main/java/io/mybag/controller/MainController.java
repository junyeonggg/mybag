package io.mybag.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	// 홈 로그인 페이지
	@GetMapping("/")
	public String home() {
		return "redirect:/login";
	}
	

	
}
