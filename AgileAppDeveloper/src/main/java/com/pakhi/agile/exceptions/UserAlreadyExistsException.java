package com.pakhi.agile.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String emailId) {
		super(emailId + "already exists");
	}
}
