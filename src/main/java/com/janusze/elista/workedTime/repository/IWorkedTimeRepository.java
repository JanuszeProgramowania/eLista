package com.janusze.elista.workedTime.repository;

import com.janusze.elista.workedTime.ob.WorkedTimeOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
@Repository
public interface IWorkedTimeRepository extends JpaRepository<WorkedTimeOB, Long> {

    @Query("SELECT  a FROM WorkedTimeOB a WHERE  a.user.id = ?1")
    List<WorkedTimeOB> findByUserId(Long aId);

    @Query("SELECT a FROM WorkedTimeOB a WHERE a.user.name = ?1 AND a.user.lastName = ?2")
    List<WorkedTimeOB> findByUserFullName(String aName, String aLastName);
}
