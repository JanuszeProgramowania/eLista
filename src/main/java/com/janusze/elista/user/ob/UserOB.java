package com.janusze.elista.user.ob;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@Entity
@Table(name = "users")
@SequenceGenerator(allocationSize = 1, name = "SEQ", sequenceName = "GEN_USER_ID")
// wraz z ponizszym wpisem zapewniaja automatyczne nadawanie kolejnych ID tworzonym rekordom
public class UserOB implements Serializable { // interfejs wymagany dla obiektow ktore beda podlegac serializacji

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    private Long id;
    @Column(name = "TECHDATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date techDate;
    private String name;
    private String lastName;


    public UserOB() {
    }

    public UserOB(String aName, String aLastName) {
        name = aName;
        lastName = aLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long aId) {
        id = aId;
    }

    public Date getTechDate() {
        return techDate;
    }

    public void setTechDate(Date aTechDate) {
        techDate = aTechDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String aLastName) {
        lastName = aLastName;
    }

    @PrePersist
    @PreUpdate
    private void setCurrentDate() {
        // przy kazdym wpisie lub zmianie rekordu bedzie aktualizowana jego data, przydatne pole przy utrzymywaniu systemu
        techDate = new Date();
    }
}
