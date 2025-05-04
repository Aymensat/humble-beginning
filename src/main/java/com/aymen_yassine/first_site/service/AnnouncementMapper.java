package com.aymen_yassine.first_site.service;

import com.aymen_yassine.first_site.DTO.AnnouncementDTOForAdmins;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForStudents;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForTeachers;
import com.aymen_yassine.first_site.entity.Announcement;
import com.aymen_yassine.first_site.entity.AppEnums;
import com.aymen_yassine.first_site.entity.StudentGroup;
import com.aymen_yassine.first_site.entity.Teacher;
import com.aymen_yassine.first_site.repository.AnnouncementRepository;
import com.aymen_yassine.first_site.repository.StudentGroupRepository;
import com.aymen_yassine.first_site.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;


@Service
public class AnnouncementMapper {

    private final StudentGroupRepository studentGroupRepository;
    private final TeacherRepository teacherRepository;

    public AnnouncementMapper(StudentGroupRepository studentGroupRepository, TeacherRepository teacherRepository) {
        this.studentGroupRepository = studentGroupRepository;
        this.teacherRepository = teacherRepository;
    }



    public AnnouncementDTOForStudents toAnnouncementDTOForStudents (Announcement announcement ) {


        if (announcement == null) return null;

        LocalDateTime date = announcement.getTargetedDate();
        String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();


        AnnouncementDTOForStudents announcementDTOForStudents = new AnnouncementDTOForStudents(

                announcement.getTeacher().getFullName(),
                announcement.getTeacher().getEmail(),
                announcement.getType(),
                announcement.getTargetedDate() ,
                weekday,
                announcement.getStudentComment()

        );

        return announcementDTOForStudents;
    }

    public AnnouncementDTOForAdmins toAnnouncementDTOForAdmins (Announcement announcement ) {

        if (announcement == null) return null;

        LocalDateTime date = announcement.getTargetedDate();
        String weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();


        AnnouncementDTOForAdmins announcementDTOForAdmins = new AnnouncementDTOForAdmins(


                announcement.getId(),

                announcement.getTeacher().getFullName() ,
                announcement.getTeacher().getEmail(),

                announcement.getStudentGroup().getClassYear(),
                announcement.getStudentGroup().getField(),
                announcement.getStudentGroup().getClassLetter(),

                announcement.getTargetedDate(),
                weekday ,

                announcement.getType(),
                announcement.getState(),

                announcement.getStudentComment(),
                announcement.getAdministratorComment(),



                announcement.getCreatedAt()

        );

    return announcementDTOForAdmins;

    }


    public Announcement toAnnouncement (AnnouncementDTOForTeachers announcementDTOForTeachers) {
        if (announcementDTOForTeachers == null) return null;

        StudentGroup studentGroup = (StudentGroup) studentGroupRepository.findByClassYearAndFieldAndClassLetter(announcementDTOForTeachers.classYear(), announcementDTOForTeachers.field() , announcementDTOForTeachers.classLetter());

        Announcement announcement = new Announcement(

//remplace with perheps from secutiy context
                teacherRepository.findById(1).orElse(null) ,

                studentGroup ,

                announcementDTOForTeachers.targetedDate(),

                announcementDTOForTeachers.type() ,
                AppEnums.AnnouncementStateEnum.pending ,


                announcementDTOForTeachers.studentComment(),
                announcementDTOForTeachers.administratorComment(),

                LocalDateTime.now()

        );

    return announcement;
    }



    public Announcement toAnnouncement(AnnouncementDTOForTeachers dto, Teacher teacher) {
        if (dto == null || teacher == null) return null;

        StudentGroup studentGroup = (StudentGroup) studentGroupRepository.findByClassYearAndFieldAndClassLetter(
                dto.classYear(), dto.field(), dto.classLetter()
        );

        return new Announcement(
                teacher,
                studentGroup,
                dto.targetedDate(),
                dto.type(),
                AppEnums.AnnouncementStateEnum.pending,
                dto.studentComment(),
                dto.administratorComment(),
                LocalDateTime.now()
        );
    }


}
