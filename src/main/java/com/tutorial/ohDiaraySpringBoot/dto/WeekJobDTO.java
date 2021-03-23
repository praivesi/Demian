package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.WeekJob;
import java.sql.Timestamp;

public class WeekJobDTO {
    private Long id;
    private String title;
    private String content;
    private Timestamp from;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    private Timestamp to;

    public static WeekJobDTO of(WeekJob week){
        WeekJobDTO dto = new WeekJobDTO();

        dto.id = week.getId();
        dto.title = week.getTitle();
        dto.content = week.getContent();
        dto.from = week.getFrom();
        dto.to = week.getTo();

        return dto;
    }
}
