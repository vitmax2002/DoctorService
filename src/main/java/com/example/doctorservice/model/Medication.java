package com.example.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;

    @NotBlank
    @Size(min = 3,message = "Descriptia trebuie sa contina cel putin 3 caractere")
    private String description;

    @Positive(message = "Dosage trebuie sa fie pozitiv")
    @NotNull
    private Float dosage;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Unit unit;

    @FutureOrPresent(message = "Time trebuie sa fie in prezent sau viitor")
    private LocalDateTime time;
    @PastOrPresent(message="CreationDate trebuie sa fie in trecut sau prezent")
    private LocalDate creationDate;

    @PastOrPresent(message="ModifyDate trebuie sa fie in trecut sau prezent")
    private LocalDate modifyDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Medication() {
    }

    public Medication(String description, @NotNull Float dosage, @NotNull Unit unit, LocalDateTime time, LocalDate creationDate, LocalDate modifyDate, Patient patient) {
        this.description = description;
        this.dosage = dosage;
        this.unit = unit;
        this.time = time;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.patient = patient;
    }

    @JsonIgnore
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getDosage() {
        return dosage;
    }

    public void setDosage(Float dosage) {
        this.dosage = dosage;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Objects.equals(medicationId, that.medicationId) && Objects.equals(description, that.description) && Objects.equals(dosage, that.dosage) && unit == that.unit && Objects.equals(time, that.time) && Objects.equals(creationDate, that.creationDate) && Objects.equals(modifyDate, that.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationId, description, dosage, unit, time, creationDate, modifyDate);
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medicationId=" + medicationId +
                ", description='" + description + '\'' +
                ", dosage=" + dosage +
                ", unit=" + unit +
                ", time=" + time +
                ", creationDate=" + creationDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
