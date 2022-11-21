package com.oraclejava.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//로그인 폼
public class LoginForm {
	// 폼점검
	@NotNull(message = "로그인 아이디 입력은 필수입니다.")
	@Size(min = 1, message = "아이디는 1자이상 입력해주세요")
	private String login;

	@Size(min = 5, message = "비밀번호는 최소 5자이상 입력해주세요")
	@NotNull(message = "비밀번호 입력은 필수입니다.")
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
