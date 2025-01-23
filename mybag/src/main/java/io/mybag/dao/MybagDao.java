package io.mybag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.mybag.dto.CategoryDto;

@Mapper
public interface MybagDao {

	@Select("SELECT * FROM category_tbl where username=#{username}")
	List<CategoryDto> getCategoryByUsername(String username);

	@Insert("INSERT INTO category_tbl(content,username,type,parent_id) VALUES(#{content},#{username},#{type},#{parent_id})")
	void saveCategory(CategoryDto categoryDto);

}
