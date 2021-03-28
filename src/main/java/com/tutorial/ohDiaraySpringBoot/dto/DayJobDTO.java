package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.DayJob;
import java.util.Date;

public class DayJobDTO {
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

    private Long id;
    private String title;
    private String content;
    private Date from;
    private Date to;

    public static DayJobDTO of(DayJob day) {
        DayJobDTO dto = new DayJobDTO();

        dto.id = day.getId();
        dto.title = day.getTitle();
        dto.content = day.getContent();
        dto.from = day.getFromTime();
        dto.to = day.getToTime();

        return dto;
    }
}
