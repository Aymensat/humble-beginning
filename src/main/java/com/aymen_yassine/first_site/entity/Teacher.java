package com.aymen_yassine.first_site.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 225)
    private String fullName;


    @OneToMany(mappedBy ="teacher")
    private List<Announcement> announcements;

    @OneToMany(mappedBy ="teacher")
    private List<Timetable> timetables;

    @Column(unique = true)
    private String email;


    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //no arg constructor
    public Teacher() {
    }

    // pre persist

    @PrePersist
    public void setDefaultEmail() {
        if (this.email == null || this.email.isBlank()) {
            this.email = this.fullName+ "@enicar.ucar.tn";
        }
    }

    //getters and setters


    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

