package com.janusze.elista.absence.repository;

import com.janusze.elista.absence.ob.AbsenceOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
@Repository
public interface IAbsenceRepository extends JpaRepository<AbsenceOB, Long> {

    @Query("SELECT  a FROM AbsenceOB a WHERE  a.user.id = ?1")
    List<AbsenceOB> findByUserId(Long aId);

    @Query("SELECT a FROM AbsenceOB a WHERE a.user.name = ?1 AND a.user.lastName = ?2")
    List<AbsenceOB> findByUserFullName(String aName, String aLastName);
}

