package com.aymen_yassine.first_site.service;

import com.aymen_yassine.first_site.DTO.TimetableDTO;
import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.Timetable;
import com.aymen_yassine.first_site.repository.StudentGroupRepository;
import com.aymen_yassine.first_site.repository.TeacherRepository;
import com.aymen_yassine.first_site.repository.TimetableRepository;
import org.springframework.stereotype.Service;
import com.aymen_yassine.first_site.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TimeTableResolver
{

    final private TimetableRepository timetableRepository;

    final private TeacherRepository teacherRepository ;
    final private StudentGroupRepository studentGroupRepository ;

    final private TimeTableMapper timeTableMapper;

    public TimeTableResolver(TimetableRepository timetableRepository, TeacherRepository teacherRepository, StudentGroupRepository studentGroupRepository, TimeTableMapper timeTableMapper) {
        this.timetableRepository = timetableRepository;
        this.teacherRepository = teacherRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.timeTableMapper = timeTableMapper;
    }

    public List<TimetableDTO>  neededTable (AppEnums.ClassYearEnum classYear, AppEnums.FieldEnum field, AppEnums.ClassLetterEnum classLetter ) {

        List<Timetable> timetables = timetableRepository.findByStudentGroupClassYearAndStudentGroupFieldAndStudentGroupClassLetter(
                 classYear,  field,  classLetter) ;

        List<TimetableDTO> timetableDTOs = timetables.stream().
        map(timeTableMapper::toTimeTableDTO)
        .collect(Collectors.toList()) ;

        return timetableDTOs ;
    }
}
