package com.oraclejava.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Review implements Serializable{

	private Integer id;
	private Integer cosId;
	private String name;
	private Date r_date;
	private String content;
	private List<ReviewComment> reviewCommentList;
	
	
	public Review(Integer id, Integer cosId, String name, Date r_date, String content,
			List<ReviewComment> reviewCommentList) {
		super();
		this.id = id;
		this.cosId = cosId;
		this.name = name;
		this.r_date = r_date;
		this.content = content;
		this.reviewCommentList = reviewCommentList;
	}


	public Review() {
		super();
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCosId() {
		return cosId;
	}
	public void setCosId(Integer cosId) {
		this.cosId = cosId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<ReviewComment> getReviewCommentList() {
		return reviewCommentList;
	}
	public void setReviewCommentList(List<ReviewComment> reviewCommentList) {
		this.reviewCommentList = reviewCommentList;
	}


	@Override
	public String toString() {
		return "Review [id=" + id + ", cosId=" + cosId + ", name=" + name + ", r_date=" + r_date + ", content="
				+ content + ", reviewCommentList=" + reviewCommentList + "]";
	}
	
	
	
	
	
	
	
	
	
}