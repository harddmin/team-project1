package com.oraclejava.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oraclejava.domain.Review;

@Repository
public class JdbcReviewRepository implements ReviewRepository {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Review> findAll() {
		String sql = "select * from review order by id desc";
		return jdbcTemplate.query(sql, this::mapRowToReview);
	}
	
	private Review mapRowToReview(ResultSet rs, int rowNum)
	throws SQLException {
		
		Review review = new Review();
		
		review.setId(rs.getInt("id"));
		review.setCosId(rs.getInt("cos_id"));
		review.setName(rs.getString("name"));
		review.setR_date(rs.getDate("r_date"));
		review.setContent(rs.getString("content"));
		return review;
	}
	
	//글쓰기 추가하기
		@Override
		public Review save(Review review) {
			System.out.println(review);
			
			String sql = "insert into review(id, cos_id, name, r_date, content) "
					+ "values(review_seq.nextval, ?, ?, TO_DATE(?,'YYYY-MM-DD') ,?)";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			 
			String s = sdf.format(review.getR_date());
			
			jdbcTemplate.update(sql, review.getCosId(), review.getName(),s, review.getContent());
			return review;
		}

		//상세화면
		@Override
		public Review findOne(int id) {
			String sql = "select id, cos_id, name, r_date, content from review "
					+ " where id=?";
			
			return jdbcTemplate.queryForObject(sql, this::mapRowToReview, id);
		}

		//글삭제
		@Override
		public void deleteById(int id) {
			String sql = "delete from review where id=?";
			jdbcTemplate.update(sql, id);
			
		}
}