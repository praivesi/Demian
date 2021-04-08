package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.Desire;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
public class DesireDTO {
    private Long id;
    private String title;
    private String content;
    private Long sortNum;

    public DesireDTO() {
        this.title = "";
        this.content = "";
        this.sortNum = -1l;
    }

    public DesireDTO(Desire desire){
        this.id = desire.getId();
        this.title = desire.getTitle();
        this.content = desire.getContent();
        this.sortNum = desire.getSortNum();
    }

    public DesireDTO(String title, String content, Long sortNum, Date fromTime, Date toTime) {
        this.title = title;
        this.content = content;
        this.sortNum = sortNum;
    }

    public Desire getEntity(){
        Desire entity = new Desire();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setSortNum(this.sortNum);

        return entity;
    }

    public static DesireDTO of(Desire desire) {
        DesireDTO dto = new DesireDTO();

        dto.id = desire.getId();
        dto.title = desire.getTitle();
        dto.content = desire.getContent();
        dto.sortNum = desire.getSortNum();

        return dto;
    }
}
