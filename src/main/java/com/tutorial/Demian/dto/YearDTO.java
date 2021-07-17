package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.YearGrowth;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.tutorial.Demian.model.Desire.DESIRE_DEFAULT_ID;
import static com.tutorial.Demian.model.YearGrowth.YEAR_JOB_DEFAULT_ID;

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

    public YearGrowth getEntity(){
        YearGrowth entity = new YearGrowth();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setFromTime(this.fromTime);
        entity.setToTime(this.toTime);

        return entity;
    }

    public static YearDTO of(YearGrowth yearGrowth){
        YearDTO dto = new YearDTO();

        dto.id = yearGrowth.getId();
        dto.desireId = yearGrowth.getDesire().getId();
        dto.title = yearGrowth.getTitle();
        dto.content = yearGrowth.getContent();
        dto.fromTime = yearGrowth.getFromTime();
        dto.toTime = yearGrowth.getToTime();

        return dto;
    }
}












