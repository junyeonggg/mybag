package io.mybag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.mybag.dto.CategoryDto;
import io.mybag.dto.ContentDto;
import io.mybag.service.MybagService;

@RestController
@RequestMapping("/api/mybag")
public class MybagRestController {
	@Autowired
	private MybagService mybagService;
	@PostMapping("")
	public ResponseEntity<Object> saveCategory(@RequestBody CategoryDto categoryDto){
		return ResponseEntity.ok(mybagService.saveCategory(categoryDto));
	}
	@GetMapping("/{id}")
	public ResponseEntity<Object> getContent(@PathVariable("id") int id){
		System.out.println("id : " + id);
		String content = mybagService.getContent(id);
		System.out.println("들어옴 content : "+content);
		return ResponseEntity.ok(content);
	}
	@PutMapping("/content")
	public ResponseEntity<Object> putContent(@RequestBody ContentDto contentDto){
		System.out.println("put 요청 들어옴 : "+ contentDto.toString());
		mybagService.putContent(contentDto);
		
		return ResponseEntity.ok(null);
	}
	@PostMapping("/saveRank")
	public ResponseEntity<Object> saveRank(@RequestBody List<Integer> newRank){
		mybagService.chRank(newRank);
		return ResponseEntity.ok(null);
	}
}
