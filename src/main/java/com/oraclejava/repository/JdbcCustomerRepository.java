package com.oraclejava.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.oraclejava.domain.Customer;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {
	private JdbcTemplate jdbcTemplate;

	// 의존성 주입
	public JdbcCustomerRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

//	private static final RowMapper<Customer> CUSTOMER_ROW_MAPPER = (rs, i) -> {
//		Customer customer = new Customer();
//		customer.setId(rs.getInt("id"));
//		customer.setLogin(rs.getString("login"));
//		customer.setPassword(rs.getString("password"));
//		return customer;
//	};

	// static(은 생성자안만들어도됨 상관없음) 버전과 SQLException 버전 2가지 종류

//	@Override
//	public Customer search(String login, String password) {
//		String sql = "select *from customer where login =? and password=?";
//
//		return jdbcTemplate.queryForObject(sql, CUSTOMER_ROW_MAPPER, login, password);
//	}

	private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
		return new Customer(rs.getInt("id"), rs.getString("login"), rs.getString("password"));
	}

	@Override
	public Customer search(String login, String password) {
		String sql = "select *from customer where login =? and password=?";

		try {
			return jdbcTemplate.queryForObject(sql, this::mapRowToCustomer, login, password);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("그런아디없음");
			return null;
		}

	}

}
