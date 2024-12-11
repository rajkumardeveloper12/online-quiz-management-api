package org.jsp.quiz.exceptionclasses;

import lombok.Builder;

@Builder
public class InvalidCredentialsException extends RuntimeException{
	private String s;
	
	public InvalidCredentialsException(String s) {
		this.s=s;
	}
	@Override
	public String getMessage() {
		return s;
	}
}
