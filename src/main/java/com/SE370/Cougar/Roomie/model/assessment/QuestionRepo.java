package com.SE370.Cougar.Roomie.model.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
    Question findByQuestionNumber(int questionNumber);
}
