package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.MonthGrowth;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.tutorial.Demian.model.Desire.DESIRE_DEFAULT_ID;
import static com.tutorial.Demian.model.MonthGrowth.MONTH_JOB_DEFAULT_ID;

@Data
public class MonthGrowthDTO {
    private Long id;
    private Long desireId;
    private String title;
    private String content;
    private int yearNumber;
    private int monthNumber;

    public MonthGrowthDTO(){
        this.id = MONTH_JOB_DEFAULT_ID;
        this.desireId = DESIRE_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.yearNumber = 0;
        this.monthNumber = 0;
    }

    public MonthGrowth getEntity(){
        MonthGrowth entity = new MonthGrowth();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setYearNumber(this.yearNumber);
        entity.setMonthNumber(this.monthNumber);

        return entity;
    }

    public static MonthGrowthDTO of(MonthGrowth monthGrowth) {
        MonthGrowthDTO dto = new MonthGrowthDTO();

        dto.id = monthGrowth.getId();
        dto.desireId = monthGrowth.getDesire().getId();
        dto.title = monthGrowth.getTitle();
        dto.content = monthGrowth.getContent();
        dto.yearNumber = monthGrowth.getYearNumber();
        dto.monthNumber = monthGrowth.getMonthNumber();

        return dto;
    }
}
