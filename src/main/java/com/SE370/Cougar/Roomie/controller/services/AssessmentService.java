package com.SE370.Cougar.Roomie.controller.services;

import com.SE370.Cougar.Roomie.model.entities.Answer;
import com.SE370.Cougar.Roomie.model.repositories.AnswerRepo;
import com.SE370.Cougar.Roomie.model.repositories.QuestionRepo;
import com.SE370.Cougar.Roomie.model.DTO.AssessmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssessmentService {
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    QuestionRepo questionRepo;

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

        return assessmentForm; // filled form
    }

    public Answer submitAssessment (AssessmentForm assessmentForm, int user_id) {
        return answerRepo.save(createAnswer(assessmentForm, user_id));
    }

    public Answer createAnswer(AssessmentForm assessmentForm, int user_id) {
        return new Answer(user_id,
                assessmentForm.getAnswer1(),
                assessmentForm.getAnswer2(),
                assessmentForm.getAnswer3(),
                assessmentForm.getAnswer4());
    }
}
