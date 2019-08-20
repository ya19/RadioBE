package com.example.radiobe.models;

import android.text.format.DateFormat;

import org.threeten.bp.LocalDate;

import java.util.Date;

public class Comment {
    String user;
    String creationDateString;
    LocalDate localDate;
    long creationDate;
    String description;

//    public Comment(String user, long creationDate, String description) {
//        this.user = user;
//        this.creationDate = creationDate;
//        this.description = description;
//        this.creationDateString =  DateFormat.format("dd/MM/yyyy", new Date(creationDate)).toString();
//    }


    public Comment(String user, LocalDate localDate, String description) {
        this.user = user;
        this.localDate = localDate;
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getCreationDateString() {
        return creationDateString;
    }

    public void setCreationDateString(String creationDateString) {
        this.creationDateString = creationDateString;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
        creationDateString = DateFormat.format("dd/MM/yyyy", new Date(creationDate)).toString();
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

