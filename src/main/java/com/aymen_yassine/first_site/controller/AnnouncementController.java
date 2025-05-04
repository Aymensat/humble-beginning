package com.aymen_yassine.first_site.controller;

import com.aymen_yassine.first_site.DTO.AnnouncementDTOForAdmins;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForStudents;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForTeachers;
import com.aymen_yassine.first_site.entity.Announcement;
import com.aymen_yassine.first_site.service.AnnouncementResolver;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//approved ones for students
@CrossOrigin(origins = "*")
@RestController
public class AnnouncementController {

    private final AnnouncementResolver announcementResolver;

    public AnnouncementController(AnnouncementResolver announcementResolver) {
        this.announcementResolver = announcementResolver;
    }

//    @CrossOrigin(origins = "*")
//    @PostMapping("/teacher")
//    public Announcement sendAnnouncement(@RequestBody AnnouncementDTOForTeachers announcementDTOForTeachers) {
//
//        return this.announcementResolver.sendAnnouncement(announcementDTOForTeachers);
//    }

    @CrossOrigin(origins = "*")
    @PostMapping("/teacher")
    public Announcement sendAnnouncement(@RequestBody AnnouncementDTOForTeachers announcementDTO) {
        return this.announcementResolver.sendAnnouncement(announcementDTO);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/annoncement/{class_id}")
    public List<AnnouncementDTOForStudents> getAnnouncement(@PathVariable int class_id){


        return announcementResolver.giveAllForStudents(class_id) ;
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/administration/all-announcement")
    public List<AnnouncementDTOForAdmins> getAllAnnouncement(){

        return announcementResolver.giveAllForAdmin();
    }



}
