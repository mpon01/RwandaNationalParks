//package com.mer.RwNatParks.repository;
//
//import com.mer.RwNatParks.model.Role;
//import com.mer.RwNatParks.model.User;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class DatabaseLoader {
//    private final UserRepo userRepo;
//
//    public DatabaseLoader(UserRepo userRepo){
//        this.userRepo = userRepo;
//    }
//
//    @Bean
//    public CommandLineRunner initializeDatabase(){
//        return args -> {
//            User user1 = new User("mer@gmail.com","mer123",Role.ADMIN);
//            User user2 = new User("papiti@gmail.com","papiti123",Role.ADMIN);
//            User user3 = new User("okwaci@gmail.com","okwaci123",Role.USER);
//            User user4 = new User("nyilek@gmail.com","nyilek123",Role.USER);
//
//            userRepo.saveAll(List.of(user1,user2,user3,user4));
//            System.out.println("Sample database initialized");
//        };
//    }
//}
