package org.jsp.quiz.dao.impl;

import java.util.List;
import java.util.Optional;

import org.jsp.quiz.dao.QuestionDao;
import org.jsp.quiz.entity.AuthorisationUser;
import org.jsp.quiz.entity.Question;
import org.jsp.quiz.entity.User;
import org.jsp.quiz.repository.QuestionRepository;
import org.jsp.quiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class QuestionDaoImpl implements QuestionDao{
	
	@Autowired
	private QuestionRepository repository;
	@Autowired
	private UserRepository repo;
	@Override
	public Question save(Question question) {
		return repository.save(question);
	}

	@Override
	public List<Question> findAllQuestions() {
		return repository.findAll();
	}

	@Override
	public Optional<Question> findQuestionById(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Question> findAllActiveQuestions() {
		return repository.findAllActiveQuestions();
	}

	@Override
	public User saveUser(User user) {
		return repo.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return repo.findAll();
	}

	@Override
	public Optional<User> findByEmailandPassword(String email, String password) {
		return repo.findByEmailAndPassword(email,password);
	}


}
