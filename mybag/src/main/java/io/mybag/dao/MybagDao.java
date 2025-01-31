package io.mybag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import io.mybag.dto.CategoryDto;

@Mapper
public interface MybagDao {

	@Select("SELECT * FROM category_tbl where username=#{username} AND parent_id = 0")
	List<CategoryDto> getCategoryByUsername(String username);

	@Options(useGeneratedKeys = true , keyProperty = "id")
	@Insert("INSERT INTO category_tbl(title,username,parent_id) VALUES(#{title},#{username},#{parent_id})")
	void saveCategory(CategoryDto categoryDto);

	@Select("SELECT * FROM category_tbl where id = #{id}")
	CategoryDto findById(int id);

	@Select("SELECT * FROM category_tbl where parent_id = #{id}")
	List<CategoryDto> getCategoryById(int id);

}
