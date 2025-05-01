package com.aymen_yassine.first_site.controller;


import com.aymen_yassine.first_site.DTO.TimetableDTO;
import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.service.TimeTableResolver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TableController {

    private TimeTableResolver timeTableResolver;

    public TableController(TimeTableResolver timeTableResolver) {
        this.timeTableResolver = timeTableResolver;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello WAWA";
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
