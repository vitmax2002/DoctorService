package com.example.doctorservice.repository;

import com.example.doctorservice.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
    @Modifying
    @Query(nativeQuery = true,value = "DELETE from medication where medication_id=:id")
    void deleteMedication(@Param("id") Long id);
}
