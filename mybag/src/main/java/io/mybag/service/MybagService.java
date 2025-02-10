package io.mybag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.mybag.dao.MybagDao;
import io.mybag.dto.CategoryDto;
import io.mybag.dto.ContentDto;

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

	public String getContent(int id) {
		ContentDto contentDto = mybagDao.getContent(id);
		if (contentDto == null) {
			return "내용이 없습니다.";
		} else {
			return mybagDao.getContent(id).getContent();

		}
	}

	public void putContent(ContentDto contentDto) {
		ContentDto content = mybagDao.getContent(contentDto.getParent_id());
		if (content == null) {
			System.out.println("처음 입력하는 데이터이므로 insert");
			// Insert
			mybagDao.insertContent(contentDto);
		} else {
			// update
			System.out.println("update");
			mybagDao.updateContent(contentDto);
		}

	}

	public CategoryDto findById(int parent_id) {
		return mybagDao.findById(parent_id);
	}

	public void chRank(List<Integer> newRank) {
		for(int i = 0; i < newRank.size(); i++) {
			System.out.println(i);
			mybagDao.chRank(i,newRank.get(i));
		}
	}

}
