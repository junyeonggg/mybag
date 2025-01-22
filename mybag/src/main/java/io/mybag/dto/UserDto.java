package io.mybag.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserDto {	
	private String username; //이메일
	private String password;
	private String nickname;
}
