package com.aymen_yassine.first_site.service;

import com.aymen_yassine.first_site.DTO.*;
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


    /**
     * Updates the status of an announcement
     *
     * @param id The ID of the announcement to update
     * @param updateData The data containing the new status and optional administrator comment
     * @return The updated announcement
     * @throws ResponseStatusException if the announcement is not found
     */
    public Announcement updateAnnouncementStatus(Integer id, AnnouncementUpdateDTO updateData) {
        // Find announcement by ID
        Optional<Announcement> announcementOptional = announcementRepository.findById(id);
        if (!announcementOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Announcement not found with id: " + id);
        }

        Announcement announcement = announcementOptional.get();

        // Update the announcement state
        announcement.setState(updateData.getState());

        // Update administrator comment if provided
        if (updateData.getAdministratorComment() != null && !updateData.getAdministratorComment().trim().isEmpty()) {
            announcement.setAdministratorComment(updateData.getAdministratorComment());
        }

        // Save and return updated announcement
        return announcementRepository.save(announcement);
    }

}