package com.janusze.elista.user.dto;

import com.janusze.elista.absence.dto.AbsenceDTO;
import com.janusze.elista.schedule.dto.ScheduleDTO;
import com.janusze.elista.workedTime.dto.WorkedTimeDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private Date techDate;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private List<AbsenceDTO> absenceList;
    private List<ScheduleDTO> scheduleList;
    private List<WorkedTimeDTO> workedTimeList;
}
