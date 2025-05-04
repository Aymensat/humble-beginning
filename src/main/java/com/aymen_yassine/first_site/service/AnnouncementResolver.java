package com.aymen_yassine.first_site.service;

import com.aymen_yassine.first_site.DTO.AnnouncementDTOForAdmins;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForStudents;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForTeachers;
import com.aymen_yassine.first_site.DTO.TimetableDTO;
import com.aymen_yassine.first_site.entity.Announcement;
import com.aymen_yassine.first_site.entity.Teacher;
import com.aymen_yassine.first_site.repository.AnnouncementRepository;
import com.aymen_yassine.first_site.repository.TeacherRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AnnouncementResolver {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final TeacherRepository teacherRepository;


    public AnnouncementResolver(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper, TeacherRepository teacherRepository) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
        this.teacherRepository = teacherRepository;
    }

//
//    public Announcement sendAnnouncement(AnnouncementDTOForTeachers announcementDTOForTeachers) {
//        //jpa save
//        return announcementRepository.save(announcementMapper.toAnnouncement(announcementDTOForTeachers));
//    }


    public List<AnnouncementDTOForAdmins> giveAllForAdmin() {

        List<AnnouncementDTOForAdmins> announcements = announcementRepository.findAll().stream().
                map(announcementMapper::toAnnouncementDTOForAdmins).collect(Collectors.toList());

        return announcements;
    }

    public List<AnnouncementDTOForStudents> giveAllForStudents(Integer classId) {
        List<AnnouncementDTOForStudents> announcements = announcementRepository.findByStudentGroupId(classId).stream()
                .map(announcementMapper::toAnnouncementDTOForStudents).collect(Collectors.toList());



        return announcements;

    }


    public Announcement sendAnnouncement(AnnouncementDTOForTeachers announcementDTOForTeachers) {
        // Get the authenticated teacher's email from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Find the teacher by email
        Optional<Teacher> teacherOptional = teacherRepository.findByEmail(username);
        if (!teacherOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found");
        }

        Teacher teacher = teacherOptional.get();

        // Map DTO to entity using the authenticated teacher
        Announcement announcement = announcementMapper.toAnnouncement(announcementDTOForTeachers, teacher);

        // Save and return
        return announcementRepository.save(announcement);
    }

}