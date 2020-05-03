package com.SE370.Cougar.Roomie.controller.view;
import com.SE370.Cougar.Roomie.controller.services.UserService;
import com.SE370.Cougar.Roomie.model.DTO.CustomUserDetails;
import com.SE370.Cougar.Roomie.model.DTO.FileTypeData;
import com.SE370.Cougar.Roomie.model.DTO.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping("user/profile")
    public String showProfile(Model model) {
        try {
            model.addAttribute("profileInfo", userService.getProfile());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "profile";
    }

    @PostMapping("user/profile")
    public String submitProfile(@ModelAttribute Profile profileInfo, @RequestParam("file") MultipartFile file, Model model) throws IOException {
        userService.updateFirstTimeUser(profileInfo, file);
        return "redirect:profile";
    }
}
