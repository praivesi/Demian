package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.DayJob;
import java.sql.Timestamp;

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

    private Long id;
    private String title;
    private String content;
    private Timestamp from;
    private Timestamp to;

    public static DayJobDTO of(DayJob day) {
        DayJobDTO dto = new DayJobDTO();

        dto.id = day.getId();
        dto.title = day.getTitle();
        dto.content = day.getContent();
        dto.from = day.getFrom();
        dto.to = day.getTo();

        return dto;
    }
}
