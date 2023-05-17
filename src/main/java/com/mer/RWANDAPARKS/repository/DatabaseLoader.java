package com.mer.RWANDAPARKS.repository;

import com.mer.RWANDAPARKS.model.Role;
import com.mer.RWANDAPARKS.model.User;
import com.mer.RWANDAPARKS.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseLoader {
    private final UserRepo userRepo;

    @Autowired
    private IUserService userService;

    public DatabaseLoader(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Bean
    public CommandLineRunner initializeDatabase(){
        return args -> {
            User user1 = new User("Mer","mer@gmail.com","mer123",Role.ADMIN);
            User user2 = new User("Papiti","papiti@gmail.com","papiti123",Role.ADMIN);
            User user3 = new User("Okwaci","okwaci@gmail.com","okwaci123",Role.USER);
            User user4 = new User("Nyilek","nyilek@gmail.com","nyilek123",Role.USER);
            userService.createAdmin(user1);
            userService.createAdmin(user2);
            userService.createUser(user3);
            userService.createUser(user4);
            System.out.println("Sample database created");
        };
    }
}
