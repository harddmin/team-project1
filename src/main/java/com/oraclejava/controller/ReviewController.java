package com.oraclejava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oraclejava.domain.Review;
import com.oraclejava.domain.ReviewComment;

import com.oraclejava.form.ReviewCommentForm;
import com.oraclejava.form.ReviewForm;
import com.oraclejava.repository.ReviewCommentRepository;
import com.oraclejava.repository.ReviewRepository;

@Transactional
@RequestMapping("/board")
@Controller
public class ReviewController {

	private ReviewRepository reviewRepository;

	private ReviewCommentRepository reviewCommentRepository;

	public ReviewController(ReviewRepository reviewRepository, ReviewCommentRepository reviewCommentRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.reviewCommentRepository = reviewCommentRepository;
	}

	// 리뷰초기화
	@ModelAttribute
	public ReviewForm setReviewForm() {
		return new ReviewForm();
	}

	// 리뷰댓글초기화
	@ModelAttribute
	public ReviewCommentForm setReviewCommentForm() {
		return new ReviewCommentForm();
	}

	// 리뷰 보기
	@RequestMapping(value = "content", method = RequestMethod.GET)
	public String showContent(@RequestParam("id") Integer id, Model model) {
//		System.out.println("showContent"); //console에서 확인하고 제대로 전송 되는지 체크.
		Review review = reviewRepository.findOne(id);

		// 댓글 목록 가져오기
		List<ReviewComment> reviewCommentList = new ArrayList<>();
		reviewCommentRepository.findByReviewId(id).forEach(c -> reviewCommentList.add(c));

		review.setReviewCommentList(reviewCommentList);

		model.addAttribute("review", review);
		model.addAttribute("contents", "show :: showView");
		return "index";
	}

	// 게시글 쓰기
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("contents", "write :: writeView");
		return "index";
	}

	@RequestMapping(value = "/write_ok", method = RequestMethod.POST)
	public String write_ok(@Validated ReviewForm reviewForm, BindingResult result, Model model) {
		System.out.println("write_ok");

		System.out.println(reviewForm);

		if (result.hasErrors()) {
			return write(model);
		}

		Review review = new Review();
		BeanUtils.copyProperties(reviewForm, review);

		System.out.println(review);
		reviewRepository.save(review);
		return "redirect:/board";
	}

	// 댓글쓰기
	@RequestMapping(value = "/comment_ok", method = RequestMethod.POST)
	public String insertreviewComment(@Validated ReviewCommentForm reviewcommentForm, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return showContent(reviewcommentForm.getReview_id(), model);
		}
		ReviewComment reviewcomment = new ReviewComment();
		BeanUtils.copyProperties(reviewcommentForm, reviewcomment);
		reviewCommentRepository.save(reviewcomment);

		System.out.println(reviewcommentForm);

		return "redirect:/board/content?id=" + reviewcomment.getReview_id();
	}

	// 글 목록보기
	@RequestMapping
	public String findAll(Model model) {
		List<Review> review = new ArrayList<>();

		reviewRepository.findAll().forEach(e -> review.add(e));

		model.addAttribute("review", review);
		model.addAttribute("contents", "board :: boardView");
		return "index";
	}

	// 글삭제하기
	@RequestMapping("/deletereview")
	public String deleteReview(ReviewForm reviewForm) {
		// 자식글 삭제
		reviewCommentRepository.deleteByReviewId(reviewForm.getId());
//			int i = 1 / 0; //트랜젝션이 제대로 됐는지 테스트 하는 방법
		// 부모글 삭제
		reviewRepository.deleteById(reviewForm.getId());
		return "redirect:/board";
	}

}