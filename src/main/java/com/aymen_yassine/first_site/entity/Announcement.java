package com.aymen_yassine.first_site.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    @ManyToOne
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;



    private LocalDateTime targetedDate;



    @Enumerated(EnumType.STRING)
    private AppEnums.AnnouncementStateEnum state;


    @Column(length = 225)
    private String studentComment;

    @Column(length = 225)
    private String administratorComment;

    private LocalDateTime createdAt;

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }


    // no args constructor
    public Announcement() {
    }
    // setters and getters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }



    public LocalDateTime getTargetedDate() {
        return targetedDate;
    }

    public void setTargetedDate(LocalDateTime targetedDate) {
        this.targetedDate = targetedDate;
    }

    public AppEnums.AnnouncementStateEnum getState() {
        return state;
    }

    public void setState(AppEnums.AnnouncementStateEnum state) {
        this.state = state;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public String getAdministratorComment() {
        return administratorComment;
    }

    public void setAdministratorComment(String administratorComment) {
        this.administratorComment = administratorComment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
