package com.example.doctorservice.service;

import com.example.doctorservice.model.Medication;
import com.example.doctorservice.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> getMedication(){
        List<Medication> medications= medicationRepository.findAll();
        return medications;
    }
}
