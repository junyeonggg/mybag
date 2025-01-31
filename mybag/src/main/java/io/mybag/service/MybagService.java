package io.mybag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mybag.dao.MybagDao;
import io.mybag.dto.CategoryDto;

@Service
public class MybagService {
	@Autowired
	private MybagDao mybagDao;
	public List<CategoryDto> getCategoryByUsername(String username) {
		
		return mybagDao.getCategoryByUsername(username);
	}
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		mybagDao.saveCategory(categoryDto);
		return mybagDao.findById(categoryDto.getId());
	}
	public List<CategoryDto> getCategoryById(int id) {
		return mybagDao.getCategoryById(id);
	}

}
