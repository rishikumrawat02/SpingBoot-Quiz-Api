package com.rishabh.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rishabh.quizapp.model.Questions;

@Repository
public interface QuestionsDAO extends JpaRepository<Questions, Integer>{
	public List<Questions> findByCategory(String category);

    @Query(value = "SELECT * FROM questions WHERE category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	public List<Questions> findRandomQuestionsByCategory(int numQ, String category);
}
