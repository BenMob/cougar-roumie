package com.SE370.Cougar.Roomie.controller.view;

import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.Profile;
import com.SE370.Cougar.Roomie.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("user/profile")
    public String showProfileInfoForm(WebRequest request, Model model){

        Profile profileInfoForm = userService
                .prepareProfile((CustomUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal());

        model.addAttribute("profileInfoForm", profileInfoForm);
        return "profile";
    }

    @PostMapping("user/profile")
    public String registerProfileInfoForm(@ModelAttribute Profile profileInfoForm, Model model) {
        userService.updateFirstTimeUser(profileInfoForm);

        model.addAttribute("profileInfoForm", profileInfoForm);
        return "profile";
    }
}
