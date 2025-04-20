package com.aymen_yassine.first_site.repository;

import com.aymen_yassine.first_site.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
}
