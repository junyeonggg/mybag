package io.mybag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.mybag.dao.UserDao;
import io.mybag.dto.UserDto;

@Service
public class SignUpService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 회원가입
	public void signup(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userDao.signup(userDto);
	}
	

}
