package com.oraclejava.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oraclejava.domain.Product;

@Repository
public class JdbcProductRepository implements ProductRepository {
	private JdbcTemplate jdbcTemplate;

	private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, i) -> {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		return product;
	};

	// 의존성주입(D.I)
	public JdbcProductRepository(JdbcTemplate jdbcTemplate) { // https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=dlaxodud2388&logNo=221958843956
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Product> search(String keyword) {
		String sql = "select *from cos where name like?";

		return jdbcTemplate.query(sql, PRODUCT_ROW_MAPPER, "%" + keyword + "%");
	}

	@Override
	public Product findOne(int id) {
		String sql = "select * from cos where id=?";
		return jdbcTemplate.queryForObject(sql, PRODUCT_ROW_MAPPER, id);
	}

}
