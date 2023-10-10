package com.example.doctorservice.service;

import com.example.doctorservice.model.Patient;
import com.example.doctorservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient addPatient(Patient patient){
        patient.setCreationDate(LocalDate.now());
        patient.setModifyDate(LocalDate.now());
        patientRepository.save(patient);
        return patient;
    }
}
