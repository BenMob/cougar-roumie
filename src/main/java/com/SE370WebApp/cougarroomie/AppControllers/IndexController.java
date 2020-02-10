package com.SE370WebApp.cougarroomie.AppControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

        @RequestMapping("/") // maps startup page to index.html
        public String showIndex() {
            return "index";
        }
}
