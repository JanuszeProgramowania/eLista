package com.janusze.elista.utils.wrappers;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.workedTime.dto.WorkedTimeDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Tomasz Jodko on 2016-04-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class WorkedTimeAndUserDTO implements Serializable {
    private WorkedTimeDTO workedTime;
    private UserDTO user;
}
