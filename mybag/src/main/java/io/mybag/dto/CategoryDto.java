package io.mybag.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CategoryDto {
	private int id;
	private String content;
	private String username;
	private String type;
	private int parent_id;
}
