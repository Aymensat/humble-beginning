package com.aymen_yassine.first_site.service;

import com.aymen_yassine.first_site.DTO.TimetableDTO;
import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.Timetable;
import com.aymen_yassine.first_site.repository.StudentGroupRepository;
import com.aymen_yassine.first_site.repository.TeacherRepository;
import com.aymen_yassine.first_site.repository.TimetableRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.aymen_yassine.first_site.entity.Teacher;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public  List<TimetableDTO> findAllByTeacherId(Integer teacherId) {

        List<Timetable> l = this.timetableRepository.findAllByTeacherId(teacherId) ;

        List<TimetableDTO> lDTO = l.stream().map(timeTableMapper::toTimeTableDTO).collect(Collectors.toList());
        return lDTO;
    }

    public List<TimetableDTO>  neededTable (AppEnums.ClassYearEnum classYear, AppEnums.FieldEnum field, AppEnums.ClassLetterEnum classLetter ) {

        List<Timetable> timetables = timetableRepository.findByStudentGroupClassYearAndStudentGroupFieldAndStudentGroupClassLetter(
                 classYear,  field,  classLetter) ;

        List<TimetableDTO> timetableDTOs = timetables.stream().
        map(timeTableMapper::toTimeTableDTO)
        .collect(Collectors.toList()) ;

        return timetableDTOs ;
    }


    public List<TimetableDTO> getTeacherTimetable(HttpServletRequest request) {
        // Get the authenticated teacher's ID from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Get teacher ID from username (email)
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(username);
        if (!teacherOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }

        Integer teacherId = teacherOptional.get().getId();

        // Query the database for all timetable entries where teacherId matches

        return this.findAllByTeacherId(teacherId);
    }
}
