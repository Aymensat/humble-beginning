package com.aymen_yassine.first_site.DTO;

import com.aymen_yassine.first_site.entity.AppEnums;

import java.time.LocalTime;

public record TimetableDTO(

        Integer id ,

        //zid class year o il jaw haka
        Integer StudentGroupId,
        AppEnums.ClassYearEnum classYear ,
        AppEnums.FieldEnum field ,
        AppEnums.ClassLetterEnum classLetter,

        Integer teacherId ,
        String teacherName,


        String subject,
        LocalTime startingTime,
        AppEnums.DayOfWeekEnum dayOfWeek,
        Integer room


){}

