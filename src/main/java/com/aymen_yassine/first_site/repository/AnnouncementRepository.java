package com.aymen_yassine.first_site.repository;

import com.aymen_yassine.first_site.DTO.AnnouncementDTOForStudents;
import com.aymen_yassine.first_site.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    List<Announcement> findByStudentGroupId(Integer studentGroupId);
}
