package com.mer.RWANDAPARKS.repository;

import com.mer.RWANDAPARKS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Long> {

    public User findByEmail(String email);


}
