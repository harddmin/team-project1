package com.oraclejava.repository;

import com.oraclejava.domain.Customer;

public interface CustomerRepository {
	// 고객을 찾기

	Customer search(String login, String password);
}
