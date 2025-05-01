package com.aymen_yassine.first_site.DTO;

import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.StudentGroup;
import com.aymen_yassine.first_site.entity.Teacher;

import java.time.LocalDateTime;

public record AnnouncementDTOForStudents(



        String teacherFullName ,
        String teacherEmail ,

        AppEnums.AnnouncementTypeEnum type,

        LocalDateTime targetedDate,


        String studentComment  )
{}




