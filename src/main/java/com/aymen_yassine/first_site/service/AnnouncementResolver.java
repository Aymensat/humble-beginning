package com.aymen_yassine.first_site.service;

import com.aymen_yassine.first_site.DTO.AnnouncementDTOForAdmins;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForStudents;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForTeachers;
import com.aymen_yassine.first_site.DTO.TimetableDTO;
import com.aymen_yassine.first_site.entity.Announcement;
import com.aymen_yassine.first_site.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AnnouncementResolver {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;


    public AnnouncementResolver(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
    }


    public Announcement sendAnnouncement(AnnouncementDTOForTeachers announcementDTOForTeachers) {
        //jpa save
        return announcementRepository.save(announcementMapper.toAnnouncement(announcementDTOForTeachers));
    }

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


}