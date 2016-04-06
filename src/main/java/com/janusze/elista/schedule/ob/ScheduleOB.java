package com.janusze.elista.schedule.ob;

import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.utils.enums.EDayOfTheWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedules")
@SequenceGenerator(allocationSize = 1, name = "SEQ3", sequenceName = "GEN_SCHEDULE_ID")
public class ScheduleOB implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ3")
    private long id;
    @ManyToOne
    private UserOB user;
    @Column(name = "DAY_OF_THE_WEEK")
    private EDayOfTheWeek dayOfTheWeek;
    @Column(name = "START")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date start;
    @Column(name = "FINISH")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date finish;
    @Column(name = "TECHDATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date techDate;

    @PrePersist
    @PreUpdate
    private void setCurrentDate() {
        // przy kazdym wpisie lub zmianie rekordu bedzie aktualizowana jego data, przydatne pole przy utrzymywaniu systemu
        techDate = new Date();
    }
}
