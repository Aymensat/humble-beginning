package com.aymen_yassine.first_site.entity;


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

    //no arg constructor
    public Teacher() {
    }


    //getters and setters

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

