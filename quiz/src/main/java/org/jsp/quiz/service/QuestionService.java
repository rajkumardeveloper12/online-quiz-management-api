package org.jsp.quiz.service;


import java.util.List;

import org.jsp.quiz.dto.QuizResponse;
import org.jsp.quiz.entity.AuthorisationUser;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.entity.User;
import org.springframework.http.ResponseEntity;

public interface QuestionService {

	ResponseEntity<?> saveQuestion(Question question);

	ResponseEntity<?> findAllQuestions();

	ResponseEntity<?> findQuestionById(int id);

	ResponseEntity<?> submitQuiz(List<QuizResponse> quizResponses);

	ResponseEntity<?> takeQuiz();

	ResponseEntity<?> saveUser(User user);

	ResponseEntity<?> findAllUsers();

	ResponseEntity<?> findStudentByEmailandPassword(AuthorisationUser auth);

}
