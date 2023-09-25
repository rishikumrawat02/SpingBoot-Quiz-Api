package com.rishabh.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.quizapp.model.Questions;
import com.rishabh.quizapp.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Questions>> getAllQuestion() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
		return questionService.addQuestion(question);
	}
}
