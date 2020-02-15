package com.SE370WebApp.cougarroomie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

        @RequestMapping("/") // maps startup page to home.html
        public String showIndex() {
            return "index";
        }
        
        @RequestMapping("/profile")
        public String showProfile() {
             return "profile";
        }

        @RequestMapping("/login")
        public String showLogin() {
            return "login";
        }

        @RequestMapping("/registration")
        public String showRegister() {
            return "registration";
        }
}
