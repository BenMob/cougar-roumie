package com.SE370.Cougar.Roomie.controller.viewcontrollers;

import org.springframework.web.bind.annotation.GetMapping;

public class RecoverController {

    @GetMapping("/recover")
    String RecoverAccount(){
        return "Coming Soon!";
    }
}
