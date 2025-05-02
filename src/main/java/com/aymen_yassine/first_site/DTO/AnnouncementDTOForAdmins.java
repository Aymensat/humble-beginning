package com.aymen_yassine.first_site.DTO;

import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.StudentGroup;

import java.time.LocalDateTime;

public record AnnouncementDTOForAdmins(

        Integer id ,

        String teacherFullName ,
        String teacherEmail,

        AppEnums.ClassYearEnum classYear,
        AppEnums.FieldEnum field,
        AppEnums.ClassLetterEnum classLetter,

        LocalDateTime targetedDate,
        String weekday ,

        AppEnums.AnnouncementTypeEnum type,
        AppEnums.AnnouncementStateEnum state,

        String studentComment,
        String administratorComment,



        LocalDateTime createdAt




) {
}
