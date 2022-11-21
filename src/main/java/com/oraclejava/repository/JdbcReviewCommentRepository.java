package com.oraclejava.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oraclejava.domain.ReviewComment;

@Repository
public class JdbcReviewCommentRepository implements ReviewCommentRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcReviewCommentRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<ReviewComment> findByReviewId(int reviewId) {
		String sql = "select id, name, content, review_id " + " from reviewcomment where review_id = ?";
		return jdbcTemplate.query(sql, this::mapRowToReviewComment, reviewId);
	}

	private ReviewComment mapRowToReviewComment(ResultSet rs, int rowNum) throws SQLException {
		return new ReviewComment(rs.getInt("id"), rs.getString("name"), rs.getString("content"),
				rs.getInt("review_id"));
	}

	@Override
	public ReviewComment save(ReviewComment reviewcomment) {
		String sql = "insert into reviewcomment(id, name, content, review_id) "
				+ "values (reviewcomment_seq.nextval, ?, ?, ?)";
		jdbcTemplate.update(sql, reviewcomment.getName(), reviewcomment.getContent(), reviewcomment.getReview_id());
		return reviewcomment;
	}

	@Override
	public void deleteByReviewId(int reviewId) {
		String sql = "delete from reviewcomment where review_id = ?";
		jdbcTemplate.update(sql, reviewId);

	}

}