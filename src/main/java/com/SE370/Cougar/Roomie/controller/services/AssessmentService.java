package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.controller.components.ObjectConverter;
import com.SE370.Cougar.Roomie.model.entities.Answer;
import com.SE370.Cougar.Roomie.model.repositories.AnswerRepo;
import com.SE370.Cougar.Roomie.model.repositories.QuestionRepo;
import com.SE370.Cougar.Roomie.model.DTO.AssessmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssessmentService {
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    ObjectConverter converter;


    // Utility Functions //
    private int calculateScore(Answer ans) {
        // Calculates match score by taking average
        return (ans.getAnswer1() + ans.getAnswer2() + ans.getAnswer3() + ans.getAnswer4() + ans.getAnswer5() + ans.getAnswer6() + ans.getAnswer7()) / 7;
    }

    @Transactional
    public AssessmentForm prepareAssessment(AssessmentForm assessmentForm) {
        // Fill form with questions in the database
        assessmentForm.setQuestion1(questionRepo.
                findByQuestionNumber(1)
                .getQuestionText());

        assessmentForm.setQuestion2(questionRepo.
                findByQuestionNumber(2)
                .getQuestionText());

        assessmentForm.setQuestion3(questionRepo.
                findByQuestionNumber(3)
                .getQuestionText());

        assessmentForm.setQuestion4(questionRepo.
                findByQuestionNumber(4)
                .getQuestionText());

        assessmentForm.setQuestion5(questionRepo.
                findByQuestionNumber(5)
                .getQuestionText());

        assessmentForm.setQuestion6(questionRepo.
                findByQuestionNumber(6)
                .getQuestionText());

        assessmentForm.setQuestion7(questionRepo.
                findByQuestionNumber(7)
                .getQuestionText());

        return assessmentForm; // filled form
    }

    @Transactional
    public int submitAndCalculateScore (AssessmentForm assessmentForm, int user_id) {
        // saves to db and returns score
        return calculateScore(
                answerRepo.save(converter.convertToEntity(assessmentForm, user_id)));
    }
}
