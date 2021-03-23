package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import java.util.Date;

public class DecadeJobDTO {
    private Long id;
    private String title;
    private String content;
    private Date fromTime;
    private Date toTime;

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

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public static DecadeJobDTO of(DecadeJob decade){
        DecadeJobDTO dto = new DecadeJobDTO();

        dto.id = decade.getId();
        dto.title = decade.getTitle();
        dto.content = decade.getContent();
        dto.fromTime = decade.getFromTime();
        dto.toTime = decade.getToTime();

        return dto;
    }
}
