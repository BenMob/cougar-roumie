package com.SE370WebApp.cougarroomie;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping("/")
    public String welcome() {
        System.out.println("welcome triggered");
        return "index.html";
    }

}
