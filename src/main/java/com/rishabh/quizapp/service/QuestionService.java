package com.rishabh.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rishabh.quizapp.dao.QuestionsDAO;
import com.rishabh.quizapp.model.Questions;

@Service
public class QuestionService {
	@Autowired
	QuestionsDAO questionDao;
	
	public ResponseEntity<List<Questions>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {	
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);		
	}

	public ResponseEntity<String> addQuestion(Questions question) {
		Questions savedQuestion = questionDao.save(question);
	    
	    if(savedQuestion!=null && savedQuestion.getId()!=null){
	        return ResponseEntity.status(HttpStatus.CREATED).body("Question added successfully");
	    }else{
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add question");
	    }
	}
	
}

