package com.aymen_yassine.first_site.controller;

import com.aymen_yassine.first_site.DTO.AnnouncementDTOForAdmins;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForStudents;
import com.aymen_yassine.first_site.DTO.AnnouncementDTOForTeachers;
import com.aymen_yassine.first_site.DTO.AnnouncementUpdateDTO;
import com.aymen_yassine.first_site.entity.Announcement;
import com.aymen_yassine.first_site.service.AnnouncementResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//approved ones for students
@Slf4j
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
        log.info("announcement sent well ");
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

    /**
     * Updates the status of an announcement
     *
     * @param id The ID of the announcement to update
     * @param updateData The update data including state and optional comment
     * @return The updated announcement
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/administration/announcement/{id}")
    public ResponseEntity<Announcement> updateAnnouncementStatus(
            @PathVariable Integer id,
            @RequestBody AnnouncementUpdateDTO updateData) {

        Announcement updatedAnnouncement = announcementResolver.updateAnnouncementStatus(id, updateData);
        return ResponseEntity.ok(updatedAnnouncement);
    }



}
