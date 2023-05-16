package com.mer.RWANDAPARKS.controller;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.service.IParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ParkController {
    @Autowired
    private IParkService parkService;

    @GetMapping(value = "/admin/add-park")
    public String getAddParkPage(Model model){
        Park park = new Park();
        model.addAttribute("park", park);
        return "admin/addPark";
    }

    @PostMapping(value = "/admin/add-park")
    public String savePark(@ModelAttribute("user") Park park){
        parkService.savePark(park);
        return "redirect:/admin/home";
    }

    @GetMapping(value = "/admin/edit-park")
    public String getEditParkPage(@RequestParam("id") int id, Model theModel) {
        Optional<Park> park = parkService.getParkById(id);
        theModel.addAttribute("park", park);
        return "admin/editPark";
    }
    @GetMapping(value = "/admin/delete-park")
    public String getDeleteParkPage(Model model) {
        Park park = new Park();
        model.addAttribute("park", park);
        return "admin/deletePark";
    }
    @PostMapping(value = "/admin/delete-park")
    public String deletePark(@RequestParam("id") int id) {
        parkService.deletePark(id);
        return "redirect:/admin/home";
    }

    @GetMapping(value = "/admin/show-parks")
    public String showParks(){
        parkService.allParks();
        return "redirect:/admin/home";
    }

}
