package io.mybag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.mybag.dto.CategoryDto;
import io.mybag.dto.ContentDto;

@Mapper
public interface MybagDao {

	@Select("SELECT * FROM category_tbl where username=#{username} AND parent_id = 0 ORDER BY category_tbl.rank")
	List<CategoryDto> getCategoryByUsername(String username);

	@Select("SELECT * FROM category_tbl where parent_id = #{id} ORDER BY category_tbl.rank")
	List<CategoryDto> getCategoryById(int id);

	@Select("SELECT * FROM category_tbl where id = #{id}")
	CategoryDto findById(int id);

	@Select("SELECT * FROM content_tbl where parent_id = #{id}")
	ContentDto getContent(int id);

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("INSERT INTO category_tbl(title,username,parent_id) VALUES(#{title},#{username},#{parent_id})")
	void saveCategory(CategoryDto categoryDto);


	@Insert("INSERT INTO content_tbl (content,parent_id) VALUES(#{content},#{parent_id})")
	void insertContent(ContentDto contentDto);

	@Update("UPDATE content_tbl SET content = #{content} WHERE parent_id = #{parent_id}")
	void updateContent(ContentDto contentDto);

	@Update("UPDATE category_tbl SET category_tbl.rank = #{rank} WHERE id = #{id}")
	void chRank(@Param("rank") int rank, @Param("id") int id);

}
