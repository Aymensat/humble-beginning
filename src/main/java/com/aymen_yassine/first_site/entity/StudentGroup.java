package com.aymen_yassine.first_site.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 225)
    private String building;

    @Enumerated(EnumType.STRING)
    private AppEnums.ClassYearEnum classYear;

    @Enumerated(EnumType.STRING)
    private AppEnums.FieldEnum field;

    @Enumerated(EnumType.STRING)
    private AppEnums.ClassLetterEnum classLetter;

    @OneToMany(mappedBy = "studentGroup")
    private List<Timetable> timetables;

    @OneToMany(mappedBy = "studentGroup")
    private List<Announcement> announcements;
    //no ags constructor
    public StudentGroup() {
    }

    //getters and setters


    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }


    public AppEnums.FieldEnum getField() {
        return field;
    }

    public void setField(AppEnums.FieldEnum field) {
        this.field = field;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public AppEnums.ClassYearEnum getClassYear() {
        return classYear;
    }

    public void setClassYear(AppEnums.ClassYearEnum classYear) {
        this.classYear = classYear;
    }

    public AppEnums.ClassLetterEnum getClassLetter() {
        return classLetter;
    }

    public void setClassLetter(AppEnums.ClassLetterEnum classLetter) {
        this.classLetter = classLetter;
    }
}
