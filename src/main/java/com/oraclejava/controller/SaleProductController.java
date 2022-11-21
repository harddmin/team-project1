package com.oraclejava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oraclejava.domain.Product;
import com.oraclejava.repository.ProductRepository;

@Controller
public class SaleProductController {
	private ProductRepository productRepository;

	public SaleProductController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@RequestMapping("/saleProduct")
	public String saleProductList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {

		if (keyword == null)
			keyword = "";// 키워드가 없으면 전체 검색
		List<Product> list = productRepository.search(keyword);
		model.addAttribute("list", list);
		model.addAttribute("contents", "sale-product :: saleView");
		return "index";
	}

}
