package com.rishabh.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.quizapp.model.QuestionWrapper;
import com.rishabh.quizapp.model.Response;
import com.rishabh.quizapp.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
		
		return quizService.createQuiz(category, numQ, title);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
		
		return quizService.getQuizQuestion(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submit(@PathVariable Integer id, @RequestBody List<Response> resp){
		
		return quizService.calcResult(id, resp);
	}
}
