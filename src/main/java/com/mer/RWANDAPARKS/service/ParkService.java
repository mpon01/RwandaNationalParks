package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.Park;
import com.mer.RWANDAPARKS.repository.ParkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkService implements IParkService{
    @Autowired
    private ParkRepo parkRepo;


    @Override
    public Park savePark(Park park) {
        return parkRepo.save(park);
    }

    @Override
    public Park updatePark(Park park) {
        return parkRepo.save(park);
    }

    @Override
    public void deletePark(int id) {
        parkRepo.deleteById(id);
    }

    @Override
    public List<Park> allParks() {
        return parkRepo.findAll();
    }

    @Override
    public Optional<Park> getParkById(int parkId) {
        return parkRepo.findById(parkId);
    }
}
