package com.techxplained.springsec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("myaccount")
    public String myAccount(){
        return "Hello this is TechXplained.";
    }
}
