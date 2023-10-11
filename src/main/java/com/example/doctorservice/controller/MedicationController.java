package com.example.doctorservice.controller;

import com.example.doctorservice.model.Medication;
import com.example.doctorservice.service.MedicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping
    public ResponseEntity<List<Medication>> getAllMedications(){
        List<Medication> medications=medicationService.getMedication();
        return ResponseEntity.ok().body(medications);
    }
}
