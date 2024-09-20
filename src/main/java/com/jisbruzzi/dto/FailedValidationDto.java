package com.jisbruzzi.dto;

import java.util.List;
import java.util.stream.Stream;

public class FailedValidationDto {

	private List<String> errors;

	public void setErrors(List<String> messages) {
		this.errors = messages;
	}

	public List<String> getErrors() {
		return errors;
	}
}
