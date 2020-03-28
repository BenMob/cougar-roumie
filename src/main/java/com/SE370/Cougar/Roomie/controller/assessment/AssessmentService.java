package com.SE370.Cougar.Roomie.controller.assessment;

import com.SE370.Cougar.Roomie.model.entities.Question;
import com.SE370.Cougar.Roomie.model.repositories.AnswerRepo;
import com.SE370.Cougar.Roomie.model.repositories.QuestionRepo;
import com.SE370.Cougar.Roomie.view.AssessmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentService {
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    QuestionRepo questionRepo;

    public AssessmentForm prepareAssessment(AssessmentForm assessmentForm) {
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

        return assessmentForm;
    }

    public void submitAssessment (AssessmentForm assessmentForm, int user_id) {

    }
}
