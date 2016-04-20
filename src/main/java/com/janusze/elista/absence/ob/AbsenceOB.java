package com.janusze.elista.absence.ob;

import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.utils.enums.EAbsenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-23.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "absences")
@SequenceGenerator(allocationSize = 1, name = "SEQ2", sequenceName = "GEN_ABSENCE_ID")
public class AbsenceOB implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ2")
    private Long id;
    @Column(name = "TYPE")
    private EAbsenceType type;
    @Column(name = "DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "HOURS")
    private int hours;
    @Column(name = "TECHDATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date techDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserOB user;

    @PrePersist
    @PreUpdate
    private void setCurrentDate() {
        // przy kazdym wpisie lub zmianie rekordu bedzie aktualizowana jego data, przydatne pole przy utrzymywaniu systemu
        techDate = new Date();
    }
}
