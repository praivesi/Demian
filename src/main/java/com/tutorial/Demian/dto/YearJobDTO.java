package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.YearJob;
import java.util.Date;

public class YearJobDTO {
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

    public static YearJobDTO of(YearJob year){
        YearJobDTO dto = new YearJobDTO();

        dto.id = year.getId();
        dto.title = year.getTitle();
        dto.content = year.getContent();
        dto.from = year.getFromTime();
        dto.to = year.getToTime();

        return dto;
    }
}












