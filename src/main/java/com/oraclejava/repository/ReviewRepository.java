package com.oraclejava.repository;

import com.oraclejava.domain.Review;

public interface ReviewRepository {

	Iterable<Review> findAll();

	Review save(Review review); // 글쓰기

	Review findOne(int id); // 글 상세보기

	void deleteById(int id); // 글삭제
}