package com.example.doctorservice.repository;

import com.example.doctorservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query(nativeQuery = true,value = "SELECT * from patient where first_name=:firstName and last_name=:lastName order by first_name asc,last_name asc")
    public List<Patient> searchPatient(@Param("firstName") String firstName,@Param("lastName") String lastName);

    public List<Patient> findByFirstNameOrderByFirstNameAscLastNameAsc(String firstName);

    public List<Patient> findByLastNameOrderByFirstNameAscLastNameAsc(String lastName);

}
