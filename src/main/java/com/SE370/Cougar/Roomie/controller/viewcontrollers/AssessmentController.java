package com.SE370.Cougar.Roomie.controller.viewcontrollers;

import com.SE370.Cougar.Roomie.controller.assessment.AssessmentService;
import com.SE370.Cougar.Roomie.view.AssessmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class AssessmentController {
    @Autowired
    AssessmentService assessmentService;

    @GetMapping("/assessment")
    public String presentAssessment(WebRequest webRequest, Model model) {
        AssessmentForm assessmentForm = new AssessmentForm();
        assessmentService.prepareAssessment(assessmentForm);
        model.addAttribute("assessmentForm", assessmentForm);
        return "assessment";
    }

    @PostMapping("/submitassessment")
    public String submitAssessment(@ModelAttribute AssessmentForm assessmentForm) {
        return "assessment";
    }
}
