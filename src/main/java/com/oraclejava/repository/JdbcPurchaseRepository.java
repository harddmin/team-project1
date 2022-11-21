package com.oraclejava.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oraclejava.domain.Item;

@Repository
public class JdbcPurchaseRepository implements PurchaseRepository {
	private JdbcTemplate jdbcTemplate;

	// D.I주입
	public JdbcPurchaseRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insert(List<Item> cart, String name, String address) {
		String sql = "insert into purchase(id, cos_id, cos_count, customer_name, customer_address	) values (purchase_seq.nextval,?,?,?,?)";
		for (Item item : cart) {
			jdbcTemplate.update(sql, item.getProduct().getId(), item.getCount(), name, address); // (의 순서는) sql순서에 해당되는
																									// 값
		}

	}

}
