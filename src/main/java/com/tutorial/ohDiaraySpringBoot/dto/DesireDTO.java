package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.Desire;
import java.util.Date;

public class DesireDTO {
    private Long id;
    private String title;
    private String content;
    private Long sortNum;
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

    public Long getSortNum() {
        return sortNum;
    }

    public void setSortNum(Long sortNum) {
        this.sortNum = sortNum;
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

    public static DesireDTO of(Desire desire)  {
        DesireDTO dto = new DesireDTO();

        dto.id = desire.getId();
        dto.title = desire.getTitle();
        dto.content = desire.getContent();
        dto.sortNum = desire.getSortNum();
        dto.fromTime = desire.getFromTime();
        dto.toTime = desire.getToTime();

        return dto;
    }
}
