package com.SE370WebApp.cougarroomie.controllers;

import com.SE370WebApp.cougarroomie.models.Person;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("person", new Person());
        return "registration";
    }


    @PostMapping("/registrationSubmit")
    public String regSubmit(@ModelAttribute Person person) {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(person.getEmailAddress())
                .setEmailVerified(false)
                .setPassword(person.getPassword())
                .setDisplayName(person.getName())
                .setDisabled(false);

        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().createUser(request);
            System.out.println("Successfully created new user: " + userRecord.getUid());
            return "index";
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "registration";
        }
    }

}
