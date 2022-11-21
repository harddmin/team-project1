package com.oraclejava.domain;

public class ReviewComment {

	private Integer id;
	private String name;
	private String content;
	private Integer review_id;
	
	
	public ReviewComment(Integer id, String name, String content, Integer review_id) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.review_id = review_id;
	}
	
	
	
	public ReviewComment() {
		super();
	}
	
	
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