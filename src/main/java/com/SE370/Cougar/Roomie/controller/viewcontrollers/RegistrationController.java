package com.SE370.Cougar.Roomie.controller.viewcontrollers;

import com.SE370.Cougar.Roomie.controller.services.CustomUserDetailsService;
import com.SE370.Cougar.Roomie.view.FirstTimeLoginForm;
import com.SE370.Cougar.Roomie.view.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegistrationController {
    @Autowired
    CustomUserDetailsService userService;
/*
    @GetMapping("/register")
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "/register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegistrationForm registrationForm) {
        userService.createNewUser(registrationForm);
        return "registrationSuccess";
    }
*/
/*
    @GetMapping("/user/secondaryinfo")
    public String showSecondaryInfoForm(WebRequest request, Model model){
        model.addAttribute("secondaryInfoForm", new FirstTimeLoginForm());
        return "secondaryinfo";
    }

    @PostMapping("/user/secondaryinfo")
    public String registerFirstLastGender(@ModelAttribute FirstTimeLoginForm secondaryInfoForm ) {
        userService.updateFirstTimeUser(secondaryInfoForm);
        return "profile";
    }
    */

}
