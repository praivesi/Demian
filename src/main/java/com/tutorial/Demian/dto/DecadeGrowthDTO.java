package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.DecadeGrowth;
import lombok.Data;

import static com.tutorial.Demian.model.DecadeGrowth.DECADE_JOB_DEFAULT_ID;
import static com.tutorial.Demian.model.Desire.DESIRE_DEFAULT_ID;

@Data
public class DecadeGrowthDTO {
    private Long id;
    private Long desireId;
    private String title;
    private String content;
    private int decadeNumber;

    public DecadeGrowthDTO(){
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

    public static DecadeGrowthDTO of(DecadeGrowth decadeGrowth){
        DecadeGrowthDTO dto = new DecadeGrowthDTO();

        dto.id = decadeGrowth.getId();
        dto.desireId = decadeGrowth.getDesire().getId();
        dto.title = decadeGrowth.getTitle();
        dto.content = decadeGrowth.getContent();
        dto.decadeNumber = decadeGrowth.getDecadeNumber();

        return dto;
    }
}
