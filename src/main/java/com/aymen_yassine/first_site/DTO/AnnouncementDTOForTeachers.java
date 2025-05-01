package com.aymen_yassine.first_site.DTO;

import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.StudentGroup;

import java.time.LocalDateTime;

public record AnnouncementDTOForTeachers(


        AppEnums.ClassYearEnum classYear,
        AppEnums.FieldEnum field,
        AppEnums.ClassLetterEnum classLetter,

        LocalDateTime targetedDate,

        AppEnums.AnnouncementTypeEnum type,

        String studentComment,
        String administratorComment


) {
}
