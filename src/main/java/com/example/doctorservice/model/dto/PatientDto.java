package com.example.doctorservice.model.dto;

import com.example.doctorservice.model.Gender;

import java.time.LocalDate;

public record PatientDto(Long patientId,
                         String firstName,
                         String lastName,
                         Gender gender,
                         LocalDate dateOfBirth,
                         LocalDate creationDate,
                         LocalDate modifyDate) {
}
