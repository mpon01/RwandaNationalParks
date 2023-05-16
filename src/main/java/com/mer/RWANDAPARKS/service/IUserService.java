package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.Animal;
import com.mer.RWANDAPARKS.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User createUser(User user);
//    User createUser(User user, MultipartFile profilePhoto);
    void deleteUser(int id);
    List<User> findUsers();
    Optional<User> getUserById(Long userId);
}
