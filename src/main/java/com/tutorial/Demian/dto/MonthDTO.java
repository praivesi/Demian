package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.Month;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.tutorial.Demian.model.Desire.DESIRE_DEFAULT_ID;
import static com.tutorial.Demian.model.Month.MONTH_JOB_DEFAULT_ID;

@Data
public class MonthDTO {
    private Long id;
    private Long desireId;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    public MonthDTO(){
        this.id = MONTH_JOB_DEFAULT_ID;
        this.desireId = DESIRE_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.fromTime = null;
        this.toTime = null;
    }

    public Month getEntity(){
        Month entity = new Month();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setFromTime(this.fromTime);
        entity.setToTime(this.toTime);

        return entity;
    }

    public static MonthDTO of(Month month) {
        MonthDTO dto = new MonthDTO();

        dto.id = month.getId();
        dto.desireId = month.getDesire().getId();
        dto.title = month.getTitle();
        dto.content = month.getContent();
        dto.fromTime = month.getFromTime();
        dto.toTime = month.getToTime();

        return dto;
    }
}
