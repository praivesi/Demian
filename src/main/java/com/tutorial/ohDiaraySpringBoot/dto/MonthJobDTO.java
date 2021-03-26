package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.MonthJob;
import java.sql.Timestamp;

public class MonthJobDTO {
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

    public static MonthJobDTO of(MonthJob month) {
        MonthJobDTO dto = new MonthJobDTO();

        dto.id = month.getId();
        dto.title = month.getTitle();
        dto.content = month.getContent();
        dto.from = month.getFromTime();
        dto.to = month.getToTime();

        return dto;
    }
}
