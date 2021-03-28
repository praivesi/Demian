package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.WeekJob;
import java.util.Date;

public class WeekJobDTO {
    private Long id;
    private String title;
    private String content;
    private Date from;
    private Date to;

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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public static WeekJobDTO of(WeekJob week){
        WeekJobDTO dto = new WeekJobDTO();

        dto.id = week.getId();
        dto.title = week.getTitle();
        dto.content = week.getContent();
        dto.from = week.getFromTime();
        dto.to = week.getToTime();

        return dto;
    }
}
