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
    private int decadeNumber;

    public DecadeDTO(){
        this.id = DECADE_JOB_DEFAULT_ID;
        this.desireId = DESIRE_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.decadeNumber = 0;
    }

    public DecadeGrowth getEntity(){
        DecadeGrowth entity = new DecadeGrowth();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setDecadeNumber(this.decadeNumber);

        return entity;
    }

    public static DecadeDTO of(DecadeGrowth decadeGrowth){
        DecadeDTO dto = new DecadeDTO();

        dto.id = decadeGrowth.getId();
        dto.desireId = decadeGrowth.getDesire().getId();
        dto.title = decadeGrowth.getTitle();
        dto.content = decadeGrowth.getContent();
        dto.decadeNumber = decadeGrowth.getDecadeNumber();

        return dto;
    }
}
