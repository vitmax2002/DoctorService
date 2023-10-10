package com.example.doctorservice.model.dto;

import com.example.doctorservice.model.Medication;

import java.util.List;

public record MedicationsDto (List<Medication> medications){
}
