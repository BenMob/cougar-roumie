package com.SE370.Cougar.Roomie.controller.view;

import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.DTO.FirstTimeLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public String showSecondaryInfoForm(WebRequest request, Model model){
        model.addAttribute("secondaryInfoForm", new FirstTimeLoginForm());
        return "profile";
    }

    @PostMapping("/welcome")
    public String registerFirstLastGender(@ModelAttribute FirstTimeLoginForm secondaryInfoForm ) {
        userService.updateFirstTimeUser(secondaryInfoForm);
        return "registrationSuccess";
    }
}
