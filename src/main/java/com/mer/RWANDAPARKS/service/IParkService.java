package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.model.User;

import java.util.List;
import java.util.Optional;

public interface IParkService {
    Park savePark(Park park);
    Park updatePark(Park park);
    void deletePark(int id);
    List<Park> allParks();
    Optional<Park> getParkById(int parkId);
}
