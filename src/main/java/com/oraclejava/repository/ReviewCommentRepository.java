package com.oraclejava.repository;

import com.oraclejava.domain.ReviewComment;

public interface ReviewCommentRepository {

	Iterable<ReviewComment> findByReviewId(int reviewId);

	ReviewComment save(ReviewComment reviewcomment); // 댓글쓰기

	void deleteByReviewId(int reviewId); // 게시글 id로 조회 후 댓글 삭제

}