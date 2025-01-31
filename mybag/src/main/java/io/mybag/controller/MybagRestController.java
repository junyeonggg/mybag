package io.mybag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mybag.dto.CategoryDto;
import io.mybag.service.MybagService;

@RestController
@RequestMapping("/mybag")
public class MybagRestController {
	@Autowired
	private MybagService mybagService;
	@PostMapping("")
	public ResponseEntity<Object> saveCategory(@RequestBody CategoryDto categoryDto){
		return ResponseEntity.ok(mybagService.saveCategory(categoryDto));
	}
}
