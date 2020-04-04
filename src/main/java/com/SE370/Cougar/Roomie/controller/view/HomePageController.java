package com.SE370.Cougar.Roomie.controller.view;

import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.DTO.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomePageController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "index";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegistrationForm registrationForm) {
        userService.createNewUser(registrationForm);
        return "index";
    }
}
