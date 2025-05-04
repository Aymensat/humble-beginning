package com.aymen_yassine.first_site.DTO;

import com.aymen_yassine.first_site.entity.AppEnums.AnnouncementStateEnum;

/**
 * DTO for updating an announcement's status
 */
public class AnnouncementUpdateDTO {
    private AnnouncementStateEnum state;
    private String administratorComment;

    // Default constructor required by Jackson
    public AnnouncementUpdateDTO() {
    }

    public AnnouncementUpdateDTO(AnnouncementStateEnum state, String administratorComment) {
        this.state = state;
        this.administratorComment = administratorComment;
    }

    public AnnouncementStateEnum getState() {
        return state;
    }

    public void setState(AnnouncementStateEnum state) {
        this.state = state;
    }

    public String getAdministratorComment() {
        return administratorComment;
    }

    public void setAdministratorComment(String administratorComment) {
        this.administratorComment = administratorComment;
    }
}