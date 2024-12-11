package org.jsp.quiz.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.quiz.entity.AuthorisationUser;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.entity.User;

public interface QuestionDao {

	Question save(Question question);

	List<Question> findAllQuestions();

	Optional<Question> findQuestionById(int id);

	List<Question> findAllActiveQuestions();

	User saveUser(User user);

	List<User> findAllUsers();

	Optional<User> findByEmailandPassword(String email, String password);

}
