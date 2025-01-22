package io.mybag.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.mybag.dto.UserDto;

@Mapper
public interface UserDao {
	void signup(UserDto userDto);

	@Select("SELECT * FROM user_tbl where username=#{username}")
	Optional<UserDto> findByUsername(String username);
}
