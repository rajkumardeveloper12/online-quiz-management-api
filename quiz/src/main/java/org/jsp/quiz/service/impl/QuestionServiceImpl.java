package org.jsp.quiz.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.jsp.quiz.dao.QuestionDao;
import org.jsp.quiz.dto.QuestionDto;
import org.jsp.quiz.dto.QuizResponse;
import org.jsp.quiz.dto.TakeQuiz;
import org.jsp.quiz.entity.AuthorisationUser;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.entity.User;
import org.jsp.quiz.exceptionclasses.InvalidQuestionIdException;
import org.jsp.quiz.exceptionclasses.NoQuestionFoundException;
import org.jsp.quiz.exceptionclasses.InvalidCredentialsException;
import org.jsp.quiz.responsestructure.ResponseStructure;
import org.jsp.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionDao dao;
	
	@Override
	public ResponseEntity<?> saveQuestion(Question question) {
		 question= dao.save(question);
		 return ResponseEntity.status(HttpStatus.OK)
					.body(
							ResponseStructure
							.builder()
							.httpStatus(HttpStatus.OK.value())
							.message("All Questions Found")
							.body(question)
							.build()
					);
	}

	@Override
	public ResponseEntity<?> findAllQuestions() {
//		List<Question> questions= dao.findAllQuestions();
		
		List<Question> questions=dao.findAllActiveQuestions();
		
		List<QuestionDto> dtolist=new ArrayList<>();
		for(Question q:questions) 
			dtolist.add(new QuestionDto(q.getTitle(),q.getA(),q.getB(),q.getC(),q.getD()));

		if(dtolist.isEmpty())
			throw NoQuestionFoundException.builder().s("No Question Found In Database").build();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(
						ResponseStructure
						.builder()
						.httpStatus(HttpStatus.OK.value())
						.message("All Questions Found")
						.body(dtolist)
						.build()
				);
	}

	@Override
	public ResponseEntity<?> findQuestionById(int id) {
		Optional<Question> optional=dao.findQuestionById(id);
		if(optional.isEmpty())
			throw InvalidQuestionIdException.builder().s("No Qquestion found In Database").build();
		return ResponseEntity.status(HttpStatus.OK)
				.body(
						ResponseStructure
						.builder()
						.httpStatus(HttpStatus.OK.value())
						.message("Question Found")
						.body(optional)
						.build()
					);
	}

	@Override
	public ResponseEntity<?> submitQuiz(List<QuizResponse> quizResponses) {
		int count=0;
		for(QuizResponse qr:quizResponses) {
			Optional<Question> q=dao.findQuestionById(qr.getId());
			if(q.isEmpty())
				throw InvalidQuestionIdException.builder().s("Invalid Question Id Unable to Find").build();
				
			Question question=q.get();
			if(question.getAns().equalsIgnoreCase(qr.getAns()))
				count++;
		
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().httpStatus(HttpStatus.OK.value()).message("Submition Successfull").body("Your Score :"+count).build());
	}

	@Override
	public ResponseEntity<?> takeQuiz() {
		List<Question> questions=dao.findAllActiveQuestions();
		
		Set<TakeQuiz> dtolist=new HashSet<>();
		
//		for(Question q:questions) 
//			dtolist.add(new TakeQuiz(q.getId(),q.getTitle(),q.getA(),q.getB(),q.getC(),q.getD()));
		for (int i = 0; i < 10; i++) {
		    Question q = questions.get(i);
		    dtolist.add(new TakeQuiz(q.getId(), q.getTitle(), q.getA(), q.getB(), q.getC(), q.getD()));
		}
		if(dtolist.isEmpty())
			throw NoQuestionFoundException.builder().s("No Question Found In Database").build();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(
						ResponseStructure
						.builder()
						.httpStatus(HttpStatus.OK.value())
						.message("All Questions Found")
						.body(dtolist)
						.build()
				);
	}

	@Override
	public ResponseEntity<?> saveUser(User user) {
		user=dao.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(
						ResponseStructure
						.builder()
						.httpStatus(HttpStatus.OK.value())
						.message("User Saved Successfull")
						.body(user)
						.build()
				);
	}

	@Override
	public ResponseEntity<?> findAllUsers() {
		List<User> list=dao.findAllUsers();
		if(list.isEmpty())
			throw NoQuestionFoundException.builder().s("No Users Found In Database").build();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(
						ResponseStructure
						.builder()
						.httpStatus(HttpStatus.OK.value())
						.message("All Users Found")
						.body(list)
						.build()
				);
	}

	@Override
	public ResponseEntity<?> findStudentByEmailandPassword(AuthorisationUser auth) {
		Optional<User> optional=dao.findByEmailandPassword(auth.getEmail(),auth.getPassword());
		if(optional.isEmpty())
			throw InvalidCredentialsException.builder().s("Invalid Credentials").build();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(
						ResponseStructure
						.builder()
						.httpStatus(HttpStatus.OK.value())
						.message("Login Successfully")
						.body(optional)
						.build()
				);
	}

	

}
