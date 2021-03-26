package com.tutorial.ohDiaraySpringBoot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String content;
    private Date fromTime;
    private Date toTime;
    private Long parentId;
    private int jobType; // 0 - Decade, 1 - Year, 2 - Month, 3 - Week, 4 - Day

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public int getJobType() {
        return jobType;
    }

    public void setJobType(int jobType) {
        this.jobType = jobType;
    }
}
