package io.mybag.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
			UserDto authentication = userDao.findByUsername(principal.getName())
					.orElseThrow(() -> new UsernameNotFoundException("찾을 수 없다."));
			authentication.setPassword("");
			return authentication;
		}
		return null;

	}

	// mybag 페이지
	@GetMapping("")
	public String mybag(Principal principal, Model model) {
		// Root Directory
		Optional.ofNullable(principal).ifPresent((p) -> {
			List<CategoryDto> categories = mybagService.getCategoryByUsername(p.getName());
			model.addAttribute("categories", categories);
			model.addAttribute("parent_id", 0);
			categories.forEach(c -> System.out.println("c : " + c.toString()));
		});
		return principal != null ? "mybag" : "redirect:/";
	}

	@GetMapping("/{parent_id}")
	public String mybagId(Principal principal, Model model, @PathVariable("parent_id") int parent_id) {
		Optional.ofNullable(principal).ifPresent((p) -> {
			List<CategoryDto> categories = mybagService.getCategoryById(parent_id);
			model.addAttribute("categories", categories);
			model.addAttribute("parent_id", parent_id);
			categories.forEach(c -> System.out.println("c : " + c.toString()));
		});
		return principal != null ? "mybag" : "redirect:/";
	}

	@GetMapping("/{parent_id}/{id}")
	public String mybagIdId(Principal principal, Model model, @PathVariable("parent_id") int parent_id,
			@PathVariable("id") int id) {
		Optional.ofNullable(principal).ifPresent((p) -> {
			List<CategoryDto> categories = mybagService.getCategoryById(id);
			model.addAttribute("categories", categories);
			model.addAttribute("parent_id", id);
			categories.forEach(c -> System.out.println("c : " + c.toString()));
		});
		return principal != null ? "mybag2" : "redirect:/";
	}

}
