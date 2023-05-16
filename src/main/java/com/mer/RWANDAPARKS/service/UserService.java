package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.exceptions.UserNotFoundException;
import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.Role;
import com.mer.RWANDAPARKS.model.User;
import com.mer.RWANDAPARKS.repository.AnimalRepo;
import com.mer.RWANDAPARKS.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AnimalRepo animalRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User createUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
//        user.setRole(Role.USER);
//        if (!profilePhoto.isEmpty()) {
//            String contentType = profilePhoto.getContentType();
//            if (contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/png")) {
//                try {
//                    byte[] photo = profilePhoto.getBytes();
//                    user.setPhoto(photo);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                throw new IllegalArgumentException("Only JPG/JPEG and PNG photos allowed.");
//            }
//        }
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById((long) id);
    }

    @Override
    public List<User> findUsers() {
        return userRepo.findAll();
    }


    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null) {
            new UsernameNotFoundException(String.format("USER_NOT_FOUND", email));
        } else {
            return (UserDetails) userRepo.findByEmail(email);
        }
        return (UserDetails) userRepo.findByEmail(email);
    }
}
