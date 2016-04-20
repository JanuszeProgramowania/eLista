package com.janusze.elista.schedule.dto;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.utils.enums.EDayOfTheWeek;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO implements Serializable {
    private Long id;
    private EDayOfTheWeek dayOfTheWeek;
    private Date start;
    private Date finish;
    private Date techDate;
    private UserDTO user;
}
