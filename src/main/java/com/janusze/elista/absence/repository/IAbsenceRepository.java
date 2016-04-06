package com.janusze.elista.absence.repository;

import com.janusze.elista.absence.ob.AbsenceOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
@Repository
public interface IAbsenceRepository extends JpaRepository<AbsenceOB, Long> {
}

