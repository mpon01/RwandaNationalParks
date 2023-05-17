package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void createUser(User user);
    void createAdmin(User user);
    void deleteUser(int id);
    List<User> findUsers();
    Optional<User> getUserById(Long userId);
}
