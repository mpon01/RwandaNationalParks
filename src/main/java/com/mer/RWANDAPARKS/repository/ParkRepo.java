package com.mer.RWANDAPARKS.repository;

import com.mer.RWANDAPARKS.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepo extends JpaRepository<Park, Integer> {

}
