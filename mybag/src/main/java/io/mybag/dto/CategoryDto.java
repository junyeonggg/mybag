package io.mybag.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CategoryDto {
	private int id;
	private String title;
	private String username;
	private int parent_id;
}
