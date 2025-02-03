package io.mybag.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ContentDto {
	private int id;
	private String content;
	private int parent_id;
}
