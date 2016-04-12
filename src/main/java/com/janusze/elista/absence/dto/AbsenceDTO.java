package com.janusze.elista.absence.dto;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.utils.enums.EAbsenceType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceDTO implements Serializable {
    private Long id;
    private UserDTO user;
    private EAbsenceType type;
    private Date date;
    private int hours;
    private Date techDate;
}
