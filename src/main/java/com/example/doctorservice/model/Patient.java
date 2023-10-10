package com.example.doctorservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patient")
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @NotBlank(message = "Give an firstName for patient")
    private String firstName;
    @NotBlank(message = "Give an lastName for patient")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @NotNull
    @Past
    private LocalDate dateOfBirth;


    @PastOrPresent
    private LocalDate creationDate;


    @PastOrPresent
    private LocalDate modifyDate;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Medication> medications;


    @JsonIgnore
    @AssertFalse(message = "You should have 18 years")
    public boolean isUnderage() {
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);
        return dateOfBirth.isAfter(eighteenYearsAgo);
   }

    public Patient() {
    }

    public Patient(String firstName, String lastName, @NotNull Gender gender, @NotNull LocalDate dateOfBirth, LocalDate creationDate, LocalDate modifyDate, List<Medication> medications) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.medications = medications;
    }

    public Long getPatientId() {
        return patientId;
    }


    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
        Patient patient = (Patient) o;
        return Objects.equals(patientId, patient.patientId) && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && gender == patient.gender && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(creationDate, patient.creationDate) && Objects.equals(modifyDate, patient.modifyDate) && Objects.equals(medications, patient.medications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, firstName, lastName, gender, dateOfBirth, creationDate, modifyDate, medications);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                ", creationDate=" + creationDate +
                ", modifyDate=" + modifyDate +
                ", medications=" + medications +
                '}';
    }
}
