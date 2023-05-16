package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.User;
import com.mer.RWANDAPARKS.model.Visit;
import com.mer.RWANDAPARKS.repository.UserRepo;
import com.mer.RWANDAPARKS.repository.VisitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService implements IVisitService{
    @Autowired
    private VisitRepo visitRepo;

    @Autowired
    private UserRepo userRepo;


    @Override
    public Visit saveVisitDetails(Visit visit) {
        return visitRepo.save(visit);
    }

    @Override
    public void deleteVisit(int id) {
        visitRepo.deleteById((long) id);
    }

    @Override
    public List<Visit> getAll(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        return user.get().getVisits();
    }

    @Override
    public Optional<Visit> findVisitById(int visitId) {
        return Optional.empty();
    }
}
