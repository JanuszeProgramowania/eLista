package com.janusze.elista.schedule.repository;

import com.janusze.elista.user.ob.UserOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
@Repository
public interface IScheduleRepository extends JpaRepository<UserOB, Long> {
}
