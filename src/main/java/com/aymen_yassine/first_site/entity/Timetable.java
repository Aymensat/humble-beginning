package com.aymen_yassine.first_site.entity;

import jakarta.persistence.*;
import java.time.LocalTime;


@Entity
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn ( name = "teacher_id")
    private Teacher teacher;

    @Column(length = 30)
    private String subject;

    private LocalTime startingTime;

    @Enumerated(EnumType.STRING)

    private AppEnums.DayOfWeekEnum dayOfWeek;

    private Integer room;

    @ManyToOne
    @JoinColumn( name = "student_group_id")
    private StudentGroup studentGroup;

    //no args constructor
    public Timetable() {
    }

    //getters and setters


    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalTime startingTime) {
        this.startingTime = startingTime;
    }

    public AppEnums.DayOfWeekEnum getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(AppEnums.DayOfWeekEnum dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }


}
