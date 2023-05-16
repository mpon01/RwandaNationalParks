package com.mer.RWANDAPARKS.controller;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.service.IAnimalService;
import com.mer.RWANDAPARKS.service.IParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private IAnimalService animalService;

    @Autowired
    private IParkService parkService;



    @GetMapping("/admin/allParks")
    public String viewParks(Model model){
        List<Animal> animals = animalService.allAnimals();
        model.addAttribute("animals", animals);
        return "admin/admin_home";
    }
    @GetMapping("/admin/allAnimals")
    public String viewAnimals(Model model){
        List<Park> parks = parkService.allParks();
        model.addAttribute("parks", parks);
        return "admin/admin_home";
    }


}
