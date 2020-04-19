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

    @GetMapping("/profile")
    public String showProfileInfoForm(WebRequest request, Model model){

         user = userService.loadUserByUsername(
                new User((((CustomUserDetails) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal()))).getUserName());

        Profile profileInfoForm = userService.prepareProfile(user);
        System.out.println("\n\n\n" + profileInfoForm.getFirst_name() + "\n\n\n");

        model.addAttribute("profileInfoForm", profileInfoForm);
        return "profile";
    }
/*
    @GetMapping("/profile")
    public String populateProfile(WebRequest request, Model model){
        Profile profileInfo = new Profile();
        userService.prepareProfile(profileInfo);
        model.addAttribute("profileInfo", new Profile());
        return "profile";
    }
*/
    @PostMapping("/profile")
    public String registerFirstLastGender(@ModelAttribute Profile profileInfoForm ) {
        userService.updateFirstTimeUser(profileInfoForm);
        return "redirect:/profile";
    }
}
