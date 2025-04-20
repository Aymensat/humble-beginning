package com.aymen_yassine.first_site.service;

import com.aymen_yassine.first_site.DTO.TimetableDTO;
import com.aymen_yassine.first_site.entity.Timetable;
import org.springframework.stereotype.Service;

@Service
public class TimeTableMapper
{

    public TimetableDTO toTimeTableDTO ( Timetable timetable ) {

        if (timetable == null) return null;

        TimetableDTO timetableDTO = new TimetableDTO(

                timetable.getId(),

                timetable.getStudentGroup().getId(),
                timetable.getStudentGroup().getClassYear(),
                timetable.getStudentGroup().getField(),
                timetable.getStudentGroup().getClassLetter(),


                timetable.getTeacher().getId(),
                timetable.getTeacher().getFullName() ,

                timetable.getSubject(),
                timetable.getStartingTime(),
                timetable.getDayOfWeek(),
                timetable.getRoom()
        );


        return timetableDTO ;

    }

}
