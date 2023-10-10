package com.example.doctorservice.service;

import com.example.doctorservice.model.Medication;
import com.example.doctorservice.model.Patient;
import com.example.doctorservice.model.dto.MedicationsDto;
import com.example.doctorservice.model.dto.PatientDto;
import com.example.doctorservice.repository.MedicationRepository;
import com.example.doctorservice.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    public PatientService(PatientRepository patientRepository, MedicationRepository medicationRepository) {
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
    }

    public Patient addPatient(Patient patient){
        patient.setCreationDate(LocalDate.now());
        patient.setModifyDate(LocalDate.now());
        for(Medication medication:patient.getMedications()){
            medication.setCreationDate(LocalDate.now());
            medication.setModifyDate(LocalDate.now());
            medication.setPatient(patient);
        }
        patientRepository.save(patient);
        return patient;
    }

    public Patient updatePatient(Patient patient){
        Patient foundPatient=patientRepository.findById(patient.getPatientId()).orElseThrow(()->new NoSuchElementException("Nu exista patient cu id:"+patient.getPatientId()));
        foundPatient.setFirstName(patient.getFirstName());
        foundPatient.setLastName(patient.getLastName());
        foundPatient.setGender(patient.getGender());
        foundPatient.setDateOfBirth(patient.getDateOfBirth());
        foundPatient.setModifyDate(LocalDate.now());
        foundPatient.setMedications(patient.getMedications());
        for(Medication medication:foundPatient.getMedications()){
            medication.setCreationDate(LocalDate.now());
            medication.setModifyDate(LocalDate.now());
            medication.setPatient(foundPatient);
        }
        patientRepository.save(foundPatient);
        return foundPatient;
    }

    public void deletePatient(Long id){
        patientRepository.deleteById(id);
    }

    public List<PatientDto> getPatients(String firstName,String lastName){
        List<Patient> patients;
        if(firstName!=null && lastName!=null) {
             patients = patientRepository.searchPatient(firstName, lastName);
        }
        else if(firstName!=null && lastName==null){
            patients= patientRepository.findByFirstNameOrderByFirstNameAscLastNameAsc(firstName);
        }
        else if(firstName==null && lastName!=null){
            patients=patientRepository.findByLastNameOrderByFirstNameAscLastNameAsc(lastName);
        }
        else patients=patientRepository.findAll();
        List<PatientDto> patientDtos = new ArrayList<>();
        for(Patient patient:patients){
            PatientDto patientDto=new PatientDto(patient.getPatientId(), patient.getFirstName(), patient.getLastName(),patient.getGender(),patient.getDateOfBirth(),patient.getCreationDate(),patient.getModifyDate());
            patientDtos.add(patientDto);
        }
        return patientDtos;
    }

    public Patient findById(Long id){
        Patient foundPatient =patientRepository.findById(id).orElseThrow(()->new NoSuchElementException("Nu exista Patient cu id:"+id));
        return foundPatient;
    }

    public Patient addMedicationforPatient(Long id,MedicationsDto medicationsDto){
        Patient patient=findById(id);
        patient.getMedications().addAll(medicationsDto.medications());
        for(Medication medication:patient.getMedications()){
            medication.setCreationDate(LocalDate.now());
            medication.setModifyDate(LocalDate.now());
            medication.setPatient(patient);
        }
        patientRepository.save(patient);
        return patient;
    }

    public Patient updateOneMedication(Long id,Medication medication){
        Medication foundmedication=medicationRepository.findById(medication.getMedicationId()).orElseThrow(()->new NoSuchElementException("Nu exista medicament cu id:"+medication.getMedicationId()));
        Patient patient =findById(id);
        if(patient.getMedications().contains(foundmedication)){
            foundmedication.setDescription(medication.getDescription());
            foundmedication.setTime(medication.getTime());
            foundmedication.setUnit(medication.getUnit());
            foundmedication.setModifyDate(LocalDate.now());
            foundmedication.setDosage(medication.getDosage());
        }
        else throw new NoSuchElementException("Medicamentul cu indexul "+medication.getMedicationId() +" nu apartine lui Patient cu indexul:"+id);
        medicationRepository.save(foundmedication);
        return patient;
    }

    public Patient deleteOneMedication(Long patientId, Long medicationId){
        Patient patient =findById(patientId);
        Medication medication=medicationRepository.findById(medicationId).orElseThrow(()->new NoSuchElementException("Nu exista medicament cu id:"+medicationId));
        if(patient.getMedications().contains(medication)){
            medicationRepository.delete(medication);
        }
        else throw new NoSuchElementException("Medicamentul cu indexul "+medicationId +" nu apartine lui Patient cu indexul:"+patientId);
        return patient;
    }

    public List<Medication> viewListofMedicationsFromPatient(Long id){
        Patient patient=findById(id);
        List<Medication> medications=patient.getMedications();
        return medications;
    }
}
