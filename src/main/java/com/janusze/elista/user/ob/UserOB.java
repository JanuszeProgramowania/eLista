package com.janusze.elista.user.ob;

import com.janusze.elista.absence.ob.AbsenceOB;
import com.janusze.elista.schedule.ob.ScheduleOB;
import com.janusze.elista.workedTime.ob.WorkedTimeOB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@SequenceGenerator(allocationSize = 1, name = "SEQ1", sequenceName = "GEN_USER_ID")
// wraz z ponizszym wpisem zapewniaja automatyczne nadawanie kolejnych ID tworzonym rekordom
public class UserOB implements Serializable { // interfejs wymagany dla obiektow ktore beda podlegac serializacji

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ1")
    private Long id;
    @Column(name = "TECHDATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date techDate;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<AbsenceOB> absenceList;
    @OneToMany(mappedBy = "user")
    private List<ScheduleOB> scheduleList;
    @OneToMany(mappedBy = "user")
    private List<WorkedTimeOB> workedTimeList;
    //TODO: user groups


    @PrePersist
    @PreUpdate
    private void setCurrentDate() {
        // przy kazdym wpisie lub zmianie rekordu bedzie aktualizowana jego data, przydatne pole przy utrzymywaniu systemu
        techDate = new Date();
    }
}
