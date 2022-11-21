package com.oraclejava.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oraclejava.domain.Item;

@Controller
public class CartListController {

	@RequestMapping("/cartList") // url 링크타고 index.html <a th:href="@{/cartlist}">장바구니</a> 과cartlist이름이 같은지확인
	public String listCart(Model model, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");

		if (cart == null || cart.size() == 0) {
			model.addAttribute("contents", "purchase-in :: error-cart");
			return "index";
		}

		model.addAttribute("cart", cart);
		model.addAttribute("contents", "purchase-in :: cartView"); // ::뒤에 오는 단어 cartView는 html의 <div th:fragment ~이다!!
		return "index";
	}

}
