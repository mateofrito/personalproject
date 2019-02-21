package org.wecancodeit.personalproject.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wecancodeit.personalproject.models.Review;
import org.wecancodeit.personalproject.repositories.ReviewRepository;

@Controller
public class HomeController {
	
	private ReviewRepository repository = new ReviewRepository(new ArrayList<Review>());

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/reviews") // list each review in HTML via th:each="person : ${people}"
	public String getReviews(Model model) {
		model.addAttribute("reviews", repository.getReviews());
		return "review"; 
	}

	@GetMapping("/reviews/add")
	public String getReviewForm() {	
		return "writeareview";
	
	}
	
	@PostMapping("/reviews/add")
	public String addReview(String title, int rating, String imageURL, String author, String category, String content) {
		repository.addReview(new Review(title, rating, imageURL, author,  content, content));
		return "redirect:/reviews/" + title;
	}
	
//	@GetMapping("/reviews/verify")	
//	public String getReview() {
//		return "verifyreview";		
//		
//	}
	
	@GetMapping("/reviews/{title}")	
	public String getReview(@PathVariable String title, Model model) {
		model.addAttribute("review", repository.findReview(title));
		return "verifyreview";		
	}
	
	
	
	

}

