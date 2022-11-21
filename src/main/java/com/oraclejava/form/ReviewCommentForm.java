package com.oraclejava.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewCommentForm {

	private Integer id;

	@NotNull(message = "이름을 입력해주세요")
	@Size(min = 1, max = 100, message = "이름은 1자이상 입력해주세요")
	private String name;

	@NotNull(message = "내용을 입력해주세요")
	@Size(min = 1, max = 100, message = "내용은 1자이상 입력해주세요")
	private String content;
	private Integer review_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

}