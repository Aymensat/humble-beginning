package com.aymen_yassine.first_site.controller;


import com.aymen_yassine.first_site.DTO.TimetableDTO;
import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.repository.TimetableRepository;
import com.aymen_yassine.first_site.service.TimeTableResolver;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RestController
public class TableController {


    private final TimetableRepository timetableRepository;
    private TimeTableResolver timeTableResolver;

    public TableController(TimeTableResolver timeTableResolver, TimetableRepository timetableRepository) {
        this.timeTableResolver = timeTableResolver;
        this.timetableRepository = timetableRepository;
    }

    @GetMapping("/hello")

    public String hello() {
        log.info("Handling /hello request"); return "Hello WAWA";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/timetable")
    public List<TimetableDTO> getTimetable(
            @RequestParam(value = "class-year", required = false) AppEnums.ClassYearEnum classYear,
            @RequestParam(value = "field", required = false) AppEnums.FieldEnum field,
            @RequestParam(value = "class-letter", required = false) AppEnums.ClassLetterEnum classLetter) {


        var hi =  timeTableResolver.neededTable(classYear  ,field  , classLetter);

        return  hi ;
    }

    @CrossOrigin(   origins = "*")
    @GetMapping("/timetable/teacher")
    public List<TimetableDTO> getTeacherTimetable(HttpServletRequest request) {
        // Get the authenticated teacher's ID from the security context

        // Query the database for all timetable entries where teacherId matches
        return timeTableResolver.getTeacherTimetable(request);
    }
/*
    @GetMapping("/timetable/teacher/{teacherName}")
    public List<TimetableDTO> getTimetable2(
            @PathVariable( "teacherName" ) String teacherName,
    {


        var hi =  timeTableResolver.neededTable(teacherName);

        return  hi ;
    }
*/


}
