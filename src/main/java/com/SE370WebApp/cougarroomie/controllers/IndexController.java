package com.SE370WebApp.cougarroomie.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

        @RequestMapping("/") // maps startup page to home.html
        public String showIndex() {
            return "home";
        }
}
