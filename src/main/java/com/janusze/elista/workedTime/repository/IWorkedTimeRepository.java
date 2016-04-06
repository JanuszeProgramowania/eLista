package com.janusze.elista.workedTime.repository;

import com.janusze.elista.user.ob.UserOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
@Repository
public interface IWorkedTimeRepository extends JpaRepository<UserOB, Long> {
}
