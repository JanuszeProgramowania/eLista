package com.janusze.elista.schedule.dto;

import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.utils.enums.EDayOfTheWeek;

import java.util.Date;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
public class ScheduleDTO {
    private long id;
    private UserOB user;
    private EDayOfTheWeek dayOfTheWeek;
    private Date from;
    private Date to;
    private Date techDate;
}
