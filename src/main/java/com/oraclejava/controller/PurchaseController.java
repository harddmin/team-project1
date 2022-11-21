package com.oraclejava.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oraclejava.domain.Item;
import com.oraclejava.repository.PurchaseRepository;

@Controller
public class PurchaseController {
	private PurchaseRepository purchaseRepository;

	public PurchaseController(PurchaseRepository purchaseRepository) {
		super();
		this.purchaseRepository = purchaseRepository;
	}

	@RequestMapping("/purchase")
	public String pur(@RequestParam("name") String name, @RequestParam("address") String address, HttpSession session,
			Model model) {// 구매에서 이름과 주소를 받는 메소드
		model.addAttribute("contents", "purchase-in :: purchase-out");// "purchase-in :: purchase-out" 앞에껀 html파일명 ::
																		// 뒤에껀 프레그먼트 명
		// 이름과 주소를 입력 안했을경우 유효성 검사
		if ((name.isEmpty() || address.isEmpty())) {
			model.addAttribute("contents", "purchase-in :: error-empty");
			return "index";

		}

		// 카트를 가져오기
		List<Item> cart = (List<Item>) session.getAttribute("cart");

		// 카트를 방치한 경우 에러 메시지 표시
		if (cart == null) {
			model.addAttribute("contents", "purchase-in :: error-cart");
			return "index";
		}

		purchaseRepository.insert(cart, name, address);

		// 구입이 완료된 경우 카트를 반납(장바구니 비우기)
		session.removeAttribute("cart");

		return "index";
	}

}
