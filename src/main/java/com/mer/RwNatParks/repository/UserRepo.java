package com.mer.RwNatParks.repository;

import com.mer.RwNatParks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
