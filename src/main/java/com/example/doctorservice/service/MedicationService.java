package com.example.doctorservice.service;

import com.example.doctorservice.model.Medication;
import com.example.doctorservice.repository.MedicationRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public Medication addMedication(Medication medication){
        Medication createdMedication= medicationRepository.save(medication);
        return createdMedication;
    }
}
