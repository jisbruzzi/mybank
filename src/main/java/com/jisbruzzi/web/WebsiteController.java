package com.jisbruzzi.web;

import com.jisbruzzi.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebsiteController {
	private final TransactionService transactionService;

	public WebsiteController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping("/account/{userId}")
	public String transactionsPerUser( @PathVariable String userId, Model model){
		model.addAttribute("userid",userId);
		model.addAttribute("transactions",transactionService.findAll());
		return "transactions.html";

	}
}
