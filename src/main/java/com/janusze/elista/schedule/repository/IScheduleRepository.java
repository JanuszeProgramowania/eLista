package com.janusze.elista.schedule.repository;

import com.janusze.elista.schedule.ob.ScheduleOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
@Repository
public interface IScheduleRepository extends JpaRepository<ScheduleOB, Long> {

    @Query("SELECT  a FROM ScheduleOB a WHERE  a.user.id = ?1")
    List<ScheduleOB> findByUserId(Long aId);

    @Query("SELECT a FROM ScheduleOB a WHERE a.user.name = ?1 AND a.user.lastName = ?2")
    List<ScheduleOB> findByUserFullName(String aName, String aLastName);
}
