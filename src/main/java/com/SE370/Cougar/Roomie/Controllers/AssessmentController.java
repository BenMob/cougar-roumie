package com.SE370.Cougar.Roomie.Controllers;

import com.SE370.Cougar.Roomie.Models.Question;
import com.SE370.Cougar.Roomie.Models.Assessment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

/*
This class is a REST controller: This means that you can have multiple methods in here which map
                                 to a URL requests.

Note: @RequestMapping is only for GET requests
 */

@RestController
class AssessmentController {

    @RequestMapping("/assessment")
    public List<Question> getAssessment() {
        List<Question> questions = new ArrayList<Question>();
        questions.add(new Question("Question 1", new String[]{"Option1", "Option2", "Option3", "Option4"}));
        questions.add(new Question("Question 2", new String[]{"Option1", "Option2", "Option3", "Option4"}));
        questions.add(new Question("Question 3", new String[]{"Option1", "Option2", "Option3", "Option4"}));
        questions.add(new Question("Question 4", new String[]{"Option1", "Option2", "Option3", "Option4"}));
        questions.add(new Question("Question 5", new String[]{"Option1", "Option2", "Option3", "Option4"}));
        questions.add(new Question("Question 6", new String[]{"Option1", "Option2", "Option3", "Option4"}));
        Assessment assessment = new Assessment(questions);

        return assessment.getQuestions();
    }
}