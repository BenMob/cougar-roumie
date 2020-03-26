package com.SE370.Cougar.Roomie.model.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {
    Answer findByUserId(int userId);

}
