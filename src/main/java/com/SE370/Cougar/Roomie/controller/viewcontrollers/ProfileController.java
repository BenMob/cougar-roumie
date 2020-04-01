package com.SE370.Cougar.Roomie.controller.viewcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/user/profile")
    public String profile() {
        return "profile";
    }
}
