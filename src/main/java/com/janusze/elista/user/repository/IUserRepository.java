package com.janusze.elista.user.repository;

import com.janusze.elista.user.ob.UserOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
public interface IUserRepository extends JpaRepository<UserOB, Long> {

    // metody takie jak findAll, findOne, save, delete sa metodami bazowymi klasy JpaRepository, wiec mozna ich uzywac "z marszu"

    List<UserOB> findByNameStartsWith(String aName);

    List<UserOB> findByLastNameStartsWith(String aLastName);

    @Query("SELECT u FROM UserOB u WHERE u.name = ?1 AND u.lastName = ?2")
    List<UserOB> findByFullName(String aName, String aLastName);
}