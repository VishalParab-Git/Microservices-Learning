package com.practcode.hotel.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String s) {
		super(s);
	}
	
	public ResourceNotFoundException() {
		super("Resource Not Found");
	}
	
}
