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

        // This is what your code should look like...
        Profile profileInfoForm = userService
                .prepareProfile((CustomUserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal());

        model.addAttribute("profileInfoForm", profileInfoForm);
        return "profile";

        // What you are doing here is asking the userService to look up a user
        // with the customer user object???? wtf

        /*
         user = userService.loadUserByUsername(
                new User((((CustomUserDetails) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal()))).getUserName());

        Profile profileInfoForm = userService.prepareProfile(user);

        System.out.println("\n\n\n" + profileInfoForm.getFirst_name() + "\n\n\n");

        model.addAttribute("profileInfoForm", profileInfoForm);
        return "profile";

         */
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
    public String registerFirstLastGender(@ModelAttribute Profile profileInfoForm, Model model) {
        userService.updateFirstTimeUser(profileInfoForm);

        String hide = "true";
        model.addAttribute("hide", hide);
        model.addAttribute("profileInfoForm", profileInfoForm);
        return "profile";
    }
}
