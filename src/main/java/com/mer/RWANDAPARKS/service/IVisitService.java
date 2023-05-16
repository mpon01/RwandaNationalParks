package com.mer.RWANDAPARKS.service;

import com.mer.RWANDAPARKS.model.Visit;

import java.util.List;
import java.util.Optional;

public interface IVisitService {
    Visit saveVisitDetails(Visit visit);
    void deleteVisit(int id);
    List<Visit> getAll(Long userId);
    Optional<Visit> findVisitById(int visitId);

}
