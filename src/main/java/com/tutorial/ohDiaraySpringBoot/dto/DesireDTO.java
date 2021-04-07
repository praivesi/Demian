package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.Desire;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DesireDTO {
    private Long id;
    private String title;
    private String content;
    private Long sortNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public DesireDTO() {
        this.title = "";
        this.content = "";
        this.sortNum = -1l;
        this.fromTime = new GregorianCalendar(1980, Calendar.JANUARY, 1).getTime();
        this.toTime = new GregorianCalendar(1980, Calendar.JANUARY, 2).getTime();
    }

    public DesireDTO(Desire desire){
        this.id = desire.getId();
        this.title = desire.getTitle();
        this.content = desire.getContent();
        this.sortNum = desire.getSortNum();
        this.fromTime = desire.getFromTime();
        this.toTime = desire.getToTime();
    }

    public DesireDTO(String title, String content, Long sortNum, Date fromTime, Date toTime) {
        this.title = title;
        this.content = content;
        this.sortNum = sortNum;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Desire getEntity(){
        Desire entity = new Desire();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setSortNum(this.sortNum);
        entity.setFromTime(this.fromTime);
        entity.setToTime(this.toTime);

        return entity;
    }

    public static DesireDTO of(Desire desire) {
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
