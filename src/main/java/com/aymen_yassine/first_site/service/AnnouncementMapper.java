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

import java.time.LocalDateTime;


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

        AnnouncementDTOForStudents announcementDTOForStudents = new AnnouncementDTOForStudents(


                announcement.getTeacher().getFullName(),
                announcement.getTeacher().getEmail(),
                announcement.getType(),
                announcement.getTargetedDate() ,
                announcement.getStudentComment()

        );

        return announcementDTOForStudents;
    }

    public AnnouncementDTOForAdmins toAnnouncementDTOForAdmins (Announcement announcement ) {

        if (announcement == null) return null;

        AnnouncementDTOForAdmins announcementDTOForAdmins = new AnnouncementDTOForAdmins(


                announcement.getId(),

                announcement.getTeacher().getFullName() ,
                announcement.getTeacher().getEmail(),

                announcement.getStudentGroup().getClassYear(),
                announcement.getStudentGroup().getField(),
                announcement.getStudentGroup().getClassLetter(),

                announcement.getTargetedDate(),

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

//baildha bil classe id
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

}
