package com.jisbruzzi.web;

import com.jisbruzzi.dto.FailedValidationDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public FailedValidationDto methodArgumentNotValidException(MethodArgumentNotValidException ex){
		FailedValidationDto dto = new FailedValidationDto();
		dto.setErrors(ex.getAllErrors().stream().map(e -> e.toString()).toList());
		return dto;
	}
}
