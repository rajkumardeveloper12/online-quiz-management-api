package org.jsp.quiz.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.jsp.quiz.exceptionclasses.NoQuestionFoundException;
import org.jsp.quiz.responsestructure.ResponseStructure;
import org.jsp.quiz.exceptionclasses.InvalidQuestionIdException;
import org.jsp.quiz.exceptionclasses.InvalidCredentialsException;

@RestControllerAdvice
public class QuestionExceptionHandler {
	public ResponseEntity<?> NoQuestionFoundException(NoQuestionFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ResponseStructure
						.builder()
						.httpStatus(HttpStatus.NOT_FOUND.value())
						.message("No Questions in Database")
						.body("No Questions Found in Database")
						.build()
					);
	}
	public ResponseEntity<?> InvalidQuestionIdException(InvalidQuestionIdException  e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ResponseStructure
						.builder()
						.httpStatus(HttpStatus.NOT_FOUND.value())
						.message("No Question in Database")
						.body("No Question Found in Database")
						.build()
					);
	}
	
	public ResponseEntity<?> InvalidCredentialsException(InvalidCredentialsException  e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ResponseStructure
						.builder()
						.httpStatus(HttpStatus.NOT_FOUND.value())
						.message("User Details Not Found")
						.body("No User Found in Database")
						.build()
					);
	}
	
}
