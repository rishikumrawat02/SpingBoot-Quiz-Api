package com.rishabh.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rishabh.quizapp.dao.QuestionsDAO;
import com.rishabh.quizapp.dao.QuizDao;
import com.rishabh.quizapp.model.QuestionWrapper;
import com.rishabh.quizapp.model.Questions;
import com.rishabh.quizapp.model.Quiz;
import com.rishabh.quizapp.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionsDAO questionsDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Questions> question = questionsDao.findRandomQuestionsByCategory(numQ, category);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(question);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Questions> quest = quiz.get().getQuestions();
		List<QuestionWrapper> questForUser = new ArrayList<>();
		for (Questions q : quest) {
			questForUser.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4()));
		}

		try {
			return new ResponseEntity<>(questForUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calcResult(Integer id, List<Response> resp) {
		Quiz quiz = quizDao.findById(id).get();
		List<Questions> quest = quiz.getQuestions();
		int rightAns=0; int i=0;
		for(Response response: resp) {
			if(response.getResp().equals(quest.get(i).getRightAnswer())) rightAns++;
			i++;
		}
		return new ResponseEntity<>(rightAns,HttpStatus.OK);
	}

}
