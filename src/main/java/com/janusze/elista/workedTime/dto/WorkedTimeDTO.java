package com.janusze.elista.workedTime.dto;

import com.janusze.elista.user.ob.UserOB;

import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
public class WorkedTimeDTO {
    private long id;
    private UserOB user;
    private Date day;
    private Date start;
    private Date finish;
    private String description;
    private Date techDate;
}
