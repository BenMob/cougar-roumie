package com.SE370.Cougar.Roomie.controller.viewcontrollers;

import com.SE370.Cougar.Roomie.controller.services.CustomUserDetailsService;
import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.view.AssessmentForm;
import com.SE370.Cougar.Roomie.view.FirstTimeLoginForm;
import com.SE370.Cougar.Roomie.view.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/user/secondaryinfo")
    public String showSecondaryInfoForm(WebRequest request, Model model){
        model.addAttribute("secondaryInfoForm", new FirstTimeLoginForm());
        return "secondaryinfo";
    }

    @PostMapping("/user/secondaryinfo")
    public String registerFirstLastGender(@ModelAttribute FirstTimeLoginForm secondaryInfoForm ) {

        int userId = (((CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())
                .getUser_id());

        userService.updateFirstTimeUser(secondaryInfoForm, userId);
        return "assessment";
    }


    // This will be the PUT request handler for the at secondaryinfo.html
/*
    @PutMapping(value = "/{userId}",
            consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String updateUserInfo(@PathVariable String userId, @RequestBody FirstTimeLoginForm userInfo){

        return "Success!!!!!";
    }
 */
}
