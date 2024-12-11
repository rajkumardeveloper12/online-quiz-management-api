package org.jsp.quiz.exceptionclasses;

import lombok.Builder;

@Builder
public class InvalidQuestionIdException extends RuntimeException{
	private String s;
	public InvalidQuestionIdException() {
	}
	public InvalidQuestionIdException(String s) {
		this.s = s;
	}

	@Override
	public String getMessage(){
		return s;
	}
	
}
