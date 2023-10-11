package com.example.doctorservice.controller;

import com.example.doctorservice.model.Medication;
import com.example.doctorservice.model.Patient;
import com.example.doctorservice.model.dto.MedicationsDto;
import com.example.doctorservice.model.dto.PatientDto;
import com.example.doctorservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient){
        Patient createdPatient = patientService.addPatient(patient);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{patientId}")
                .buildAndExpand(createdPatient.getPatientId())
                .toUri();
        return ResponseEntity.created(location).body(createdPatient);
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient){
        Patient updatedPatient=patientService.updatePatient(patient);
        return ResponseEntity.ok().body(updatedPatient);
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable(name = "patientId") Long patientId){
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatients(@RequestParam(required = false,name = "firstName") String firstName,@RequestParam(required = false,name = "lastName") String lastName){
        List<PatientDto> patients=patientService.getPatients(firstName,lastName);
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatient(@PathVariable(name = "patientId") Long patientId){
        Patient foundPatient=patientService.findById(patientId);
        return ResponseEntity.ok().body(foundPatient);
    }

    @GetMapping("/medications/{patientId}")
    public ResponseEntity<List<Medication>> getMedications(@PathVariable(name = "patientId") Long patientId){
        List<Medication> medications=patientService.viewListofMedicationsFromPatient(patientId);
        return ResponseEntity.ok().body(medications);
    }

   @PutMapping("/medications/{patientId}")
    public ResponseEntity<Patient> addMedicationforPatient(@PathVariable(name = "patientId") Long patientId,@Valid @RequestBody MedicationsDto medicationsDto){
        Patient patient=patientService.addMedicationforPatient(patientId,medicationsDto);
        return ResponseEntity.ok().body(patient);
   }

   @PutMapping("/medication/update/{patientId}")
   public ResponseEntity<Patient> updateOneMedication(@PathVariable(name ="patientId" ) Long id,@Valid @RequestBody Medication medication){
        Patient patient=patientService.updateOneMedication(id,medication);
        return ResponseEntity.ok().body(patient);
   }

   @DeleteMapping("medication/delete/{patientId}/{medicationId}")
    public ResponseEntity<Void> deleteOneMedication(@PathVariable(name = "patientId") Long patientId,@PathVariable(name = "medicationId") Long medicationId){
        patientService.deleteOneMedication(patientId,medicationId);
        return ResponseEntity.noContent().build();
   }
}
