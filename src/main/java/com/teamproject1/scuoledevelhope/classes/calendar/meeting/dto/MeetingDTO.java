package com.teamproject1.scuoledevelhope.classes.calendar.meeting.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MeetingDTO {
    private String titolo;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String note;

    public MeetingDTO() {
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}