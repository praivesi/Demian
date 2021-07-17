package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.Year;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.tutorial.Demian.model.DesireGrowth.DESIRE_DEFAULT_ID;
import static com.tutorial.Demian.model.Year.YEAR_JOB_DEFAULT_ID;

@Data
public class YearDTO {
    private Long id;
    private Long desireId;
    private String title;
    private String content;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    public YearDTO(){
        this.id = YEAR_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.fromTime = null;
        this.toTime = null;
        this.desireId = DESIRE_DEFAULT_ID;
    }

    public Year getEntity(){
        Year entity = new Year();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setFromTime(this.fromTime);
        entity.setToTime(this.toTime);

        return entity;
    }

    public static YearDTO of(Year year){
        YearDTO dto = new YearDTO();

        dto.id = year.getId();
        dto.desireId = year.getDesire().getId();
        dto.title = year.getTitle();
        dto.content = year.getContent();
        dto.fromTime = year.getFromTime();
        dto.toTime = year.getToTime();

        return dto;
    }
}












