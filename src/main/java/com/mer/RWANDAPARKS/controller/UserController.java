package com.mer.RWANDAPARKS.controller;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.model.User;
import com.mer.RWANDAPARKS.model.Visit;
import com.mer.RWANDAPARKS.repository.UserRepo;
import com.mer.RWANDAPARKS.service.*;
import com.mer.RWANDAPARKS.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private JavaMailSender mailSender;

//    @Autowired
//    private UserRepo userRepo;

    @Autowired
    private IUserService userService;

    @Autowired
    private IVisitService visitService;

    @Autowired
    private IAnimalService animalService;

    @Autowired
    private IParkService parkService;

    @PostMapping("/user/register")
    public String saveUser(@ModelAttribute("user") User user ) throws MessagingException {
        userService.createUser(user);
        sendEmail(user.getEmail());
        return "redirect:/user/login";
    }

//    @PostMapping("/user/register")
//    public String saveUser(@ModelAttribute("user") User user, @RequestParam("photo") MultipartFile profilePhoto) throws MessagingException {
//        if (!profilePhoto.isEmpty()) {
//            try {
//                byte[] photo = profilePhoto.getBytes();
//                user.setFileName(profilePhoto.getOriginalFilename());
//                user.setPhoto(photo);
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                String currentPrincipalName = authentication.getName();
//                user.setEmail(currentPrincipalName);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        userService.createUser(user,profilePhoto);
//        sendEmail(user.getEmail());
//        return "redirect:/user/login";
//    }

    public void sendEmail(String recipientEmail)
            throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("p.mer00256@gmail.com");
        helper.setTo(recipientEmail);

        String subject = "Rwanda National Parks | Registration Confirmation Email";

        String content = "<p>Hello there,</p>"
                + "<p>Welcome to the world of animals of Rwanda in the heart of Africa.</p>"
                + "<p>We hope you enjoy the sceneries and don't hesitate to contact us for any inquiries"
                + " on <a href='mailto:p.mer00256@gmail.com'>our email.</a></p>"
                + "<p>Thanks again!</p>"
                + "<p>Rwanda National Parks.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/user/home")
    public String viewUserHomePage(Model model, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "redirect:/user/login";
        }
        List<Visit> visits = visitService.getAll(Long.valueOf(userId));
        model.addAttribute("visit", visits);
        System.out.println("User: "+session.getAttribute("userId"));
        return "user/user_home";
    }
    @GetMapping("/user/book-visit")
    public String viewVisitPage(Model model){
        Visit visit = new Visit();
        model.addAttribute("visit", visit);
        return "user/user_book-visit";
    }


    @PostMapping("/user/book-visit")
    public String saveVisit(@ModelAttribute("visit") Visit aVisit, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "error";
        }
        Optional<User> user = userService.getUserById(Long.valueOf(userId));
        aVisit.setUser(user.get());
        Visit savedVisit = visitService.saveVisitDetails(aVisit);
        if (savedVisit != null) {
            return "redirect:/user/home";
        }
        return "error";

    }

    @GetMapping("/user/animals")
    public String viewAllAnimalsPage(Model model){
        List<Animal> animals = animalService.allAnimals();
        model.addAttribute("animals", animals);
        return "user/animals";
    }

    @GetMapping("/user/parks")
    public String viewAllParksPage(Model model){
        List<Park> parks = parkService.allParks();
        model.addAttribute("parks", parks);
        return "user/parks";
    }

}

