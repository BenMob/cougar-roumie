package com.SE370.Cougar.Roomie.controller.view;

import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.FileTypeData;
import com.SE370.Cougar.Roomie.model.DTO.Profile;
import com.SE370.Cougar.Roomie.model.entities.Image;
import com.SE370.Cougar.Roomie.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.Base64;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping("user/profile")
    public String showProfileInfoForm(WebRequest request, Model model){
        CustomUserDetails thisUser = (CustomUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();

        Profile profileInfoForm = userService
                .prepareProfile(thisUser);

        FileTypeData profileImage = userService
                .getProfileImage(thisUser);

        model.addAttribute("profileInfoForm", profileInfoForm);
        model.addAttribute("profileImage", profileImage);
        return "profile";
    }

    @PostMapping("user/profile")
    public String registerProfileInfoForm(@ModelAttribute Profile profileInfoForm, FileTypeData profileImage, Model model) throws IOException {
        userService.updateFirstTimeUser(profileInfoForm, profileImage);

        model.addAttribute("profileInfoForm", profileInfoForm);
        model.addAttribute("profileImage", profileImage);
        return "profile";
    }
}
