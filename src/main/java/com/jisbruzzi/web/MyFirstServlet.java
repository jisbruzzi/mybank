package com.jisbruzzi.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jisbruzzi.model.Transaction;
import com.jisbruzzi.service.TransactionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
public class MyFirstServlet extends HttpServlet {
	private ObjectMapper objectMapper;
	private TransactionService transactionService;

	public MyFirstServlet(ObjectMapper objectMapper, TransactionService transactionService) {
		this.objectMapper = objectMapper;
		this.transactionService = transactionService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().print(objectMapper.writeValueAsString(transactionService.findAll()));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String reference = req.getParameter("reference");
		Number amount = Float.valueOf(req.getParameter("amount"));
		Transaction created = transactionService.create(amount, reference);
		resp.setContentType("application/json; charset=UTF-8");
		String json = objectMapper.writeValueAsString(created);
		resp.getWriter().print(json);
	}
}
