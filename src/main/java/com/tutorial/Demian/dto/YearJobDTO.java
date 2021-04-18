package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.YearJob;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.tutorial.Demian.model.Desire.DESIRE_DEFAULT_ID;
import static com.tutorial.Demian.model.YearJob.YEAR_JOB_DEFAULT_ID;

@Data
public class YearJobDTO {
    private Long id;
    private Long desireId;
    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    public YearJobDTO(){
        this.id = YEAR_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.fromTime = null;
        this.toTime = null;
        this.desireId = DESIRE_DEFAULT_ID;
    }

    public YearJob getEntity(){
        YearJob entity = new YearJob();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setFromTime(this.fromTime);
        entity.setToTime(this.toTime);

        return entity;
    }

    public static YearJobDTO of(YearJob year){
        YearJobDTO dto = new YearJobDTO();

        dto.id = year.getId();
        dto.desireId = year.getDesire().getId();
        dto.title = year.getTitle();
        dto.content = year.getContent();
        dto.fromTime = year.getFromTime();
        dto.toTime = year.getToTime();

        return dto;
    }
}












