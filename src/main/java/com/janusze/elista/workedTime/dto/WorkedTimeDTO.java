package com.janusze.elista.workedTime.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.janusze.elista.user.dto.UserDTO;
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
public class WorkedTimeDTO implements Serializable {
    private Long id;
    private Date start;
    private Date finish;
    private String description;
    private Date techDate;
    @JsonIgnore
    private UserDTO user;
}
