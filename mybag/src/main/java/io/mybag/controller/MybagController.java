package io.mybag.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mybag.dao.UserDao;
import io.mybag.service.LoginService;

@Controller
@RequestMapping("/mybag")
public class MybagController {
	@Autowired
	private UserDao userDao;

	@ModelAttribute("nickname")
	public String nickname(Principal principal) {
		if (principal != null) {
			return userDao.findByUsername(principal.getName())
					.orElseThrow(() -> new UsernameNotFoundException("찾을 수 없다.")).getNickname();
		}
		return null;

	}

	// mybag 페이지
	@GetMapping("")
	public String mybag(Principal principal, Model model) {
		return "mybag";
	}
}
