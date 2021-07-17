package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.DecadeGrowth;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.tutorial.Demian.model.DecadeGrowth.DECADE_JOB_DEFAULT_ID;
import static com.tutorial.Demian.model.DesireGrowth.DESIRE_DEFAULT_ID;

@Data
public class DecadeDTO {
    private Long id;
    private Long desireId;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    public DecadeDTO(){
        this.id = DECADE_JOB_DEFAULT_ID;
        this.desireId = DESIRE_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.fromTime = null;
        this.toTime = null;
    }

    public DecadeGrowth getEntity(){
        DecadeGrowth entity = new DecadeGrowth();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setFromTime(this.fromTime);
        entity.setToTime(this.toTime);

        return entity;
    }

    public static DecadeDTO of(DecadeGrowth decadeGrowth){
        DecadeDTO dto = new DecadeDTO();

        dto.id = decadeGrowth.getId();
        dto.desireId = decadeGrowth.getDesire().getId();
        dto.title = decadeGrowth.getTitle();
        dto.content = decadeGrowth.getContent();
        dto.fromTime = decadeGrowth.getFromTime();
        dto.toTime = decadeGrowth.getToTime();

        return dto;
    }
}
