package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.MonthJob;
import java.util.Date;

public class MonthJobDTO {
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
