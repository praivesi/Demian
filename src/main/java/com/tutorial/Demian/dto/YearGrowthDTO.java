package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.YearGrowth;
import lombok.Data;

import static com.tutorial.Demian.model.Desire.DESIRE_DEFAULT_ID;
import static com.tutorial.Demian.model.YearGrowth.YEAR_JOB_DEFAULT_ID;

@Data
public class YearGrowthDTO {
    private Long id;
    private Long desireId;
    private String title;
    private String content;
    private int yearNumber;

    public YearGrowthDTO(){
        this.id = YEAR_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.yearNumber = 0;
        this.desireId = DESIRE_DEFAULT_ID;
    }

    public YearGrowth getEntity(){
        YearGrowth entity = new YearGrowth();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setYearNumber(this.yearNumber);

        return entity;
    }

    public static YearGrowthDTO of(YearGrowth yearGrowth){
        YearGrowthDTO dto = new YearGrowthDTO();

        dto.id = yearGrowth.getId();
        dto.desireId = yearGrowth.getDesire().getId();
        dto.title = yearGrowth.getTitle();
        dto.content = yearGrowth.getContent();
        dto.yearNumber = yearGrowth.getYearNumber();

        return dto;
    }
}












