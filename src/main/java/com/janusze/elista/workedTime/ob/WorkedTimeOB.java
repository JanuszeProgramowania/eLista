package com.janusze.elista.workedTime.ob;

import com.janusze.elista.user.ob.UserOB;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "WORKED_TIMES")
@SequenceGenerator(allocationSize = 1, name = "SEQ4", sequenceName = "GEN_WORKEDTIMES_ID")
public class WorkedTimeOB {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ4")
    private Long id;
    @Column(name = "START")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date start;
    @Column(name = "FINISH")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date finish;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "TECHDATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date techDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserOB user;


    @PrePersist
    @PreUpdate
    private void setCurrentDate() {
        // przy kazdym wpisie lub zmianie rekordu bedzie aktualizowana jego data, przydatne pole przy utrzymywaniu systemu
        techDate = new Date();
    }
}
