package io.mybag.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mybag.dao.UserDao;
import io.mybag.dto.CategoryDto;
import io.mybag.dto.UserDto;
import io.mybag.service.MybagService;

@Controller
@RequestMapping("/mybag")
public class MybagController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MybagService mybagService;

	@ModelAttribute("authentication")
	public UserDto nickname(Principal principal) {
		if (principal != null) {
			UserDto authentication =userDao.findByUsername(principal.getName())
					.orElseThrow(() -> new UsernameNotFoundException("찾을 수 없다."));  
			authentication.setPassword("");
			return authentication;
		}
		return null;

	}

	// mybag 페이지
	@GetMapping("")
	public String mybag(Principal principal, Model model) {
		// 카테고리 받아오기
		List<CategoryDto> categories = mybagService.getCategoryByUsername(principal.getName());
		model.addAttribute("categories", categories);
		categories.forEach(c -> System.out.println("c : " + c.toString()));
		return "mybag";
	}
}
