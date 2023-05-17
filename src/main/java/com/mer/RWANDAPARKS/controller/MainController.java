package com.mer.RWANDAPARKS.controller;

import com.mer.RWANDAPARKS.model.User;
import com.mer.RWANDAPARKS.model.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String viewHomePage(){
        return "home";
    }

    @GetMapping("/user/login")
    public String viewUserLoginPage(){return "user/user_login";}

//    @GetMapping("/user/home")
//    public String viewUserHomePage(){return "user/user_home";}

    @GetMapping("/user/signup")
    public String viewUserSignupPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/user_signup";
    }
    @GetMapping("/admin/login")
    public String viewAdminLoginPage(){
        return "admin/admin_login";
    }

    @GetMapping("/admin/home")
    public String viewAdminHomePage(){
        return "admin/admin_home";
    }

    @GetMapping("/error")
    public String errorPage(){return "error";}
}
