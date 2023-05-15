package com.mer.RwNatParks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String viewHomePage(){
        return "home";
    }

    @GetMapping("/admin/login")
    public String viewAdminLoginPage(){
        return "admin/admin_login";
    }

    @GetMapping("/user/login")
    public String viewUserLoginPage(){
        return "user/user_login";
    }

    @GetMapping("/user/sign-up")
    public String viewUserSignupPage(){
        return "user/user_sign-up";
    }

    @GetMapping("/admin/home")
    public String viewAdminHomePage(){
        return "admin/admin_home";
    }

    @GetMapping("/user/home")
    public String viewUserHomePage(){
        return "user/user_home";
    }

    @GetMapping("/error")
    public String errorPage(){return "error";}
}
