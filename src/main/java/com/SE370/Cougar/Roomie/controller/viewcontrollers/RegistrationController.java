package com.SE370.Cougar.Roomie.controller.viewcontrollers;

import com.SE370.Cougar.Roomie.controller.auth.CustomUserDetailsService;
import com.SE370.Cougar.Roomie.model.entities.User;
import com.SE370.Cougar.Roomie.view.FirstTimeLoginForm;
import com.SE370.Cougar.Roomie.view.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class RegistrationController {
    @Autowired
    CustomUserDetailsService userService;

    @GetMapping("/register")
    public String showRegistrationForm(WebRequest request, Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegistrationForm registrationForm) {
        userService.createNewUser(registrationForm);
        return "registrationSuccess";
    }
/*
    @PutMapping(value = "/{userId}",
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String updateUserInfo(@PathVariable String userId, @RequestBody FirstTimeLoginForm userInfo){

        return "Success!!!!!";
    }
 */
}
