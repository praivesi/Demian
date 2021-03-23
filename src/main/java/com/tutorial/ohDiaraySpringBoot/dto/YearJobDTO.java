package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.YearJob;
import java.sql.Timestamp;

public class YearJobDTO {
    private Long id;
    private String title;
    private String content;
    private Timestamp from;
    private Timestamp to;

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

    public static YearJobDTO of(YearJob year){
        YearJobDTO dto = new YearJobDTO();

        dto.id = year.getId();
        dto.title = year.getTitle();
        dto.content = year.getContent();
        dto.from = year.getFrom();
        dto.to = year.getTo();

        return dto;
    }
}












