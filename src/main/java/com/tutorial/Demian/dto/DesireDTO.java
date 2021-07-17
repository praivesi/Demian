package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.DesireGrowth;
import lombok.Data;

import java.util.Date;

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

    public DesireDTO(DesireGrowth desire){
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

    public DesireGrowth getEntity(){
        DesireGrowth entity = new DesireGrowth();

        entity.setId(this.id);
        entity.setTitle(this.title);
        entity.setContent(this.content);
        entity.setSortNum(this.sortNum);

        return entity;
    }

    public static DesireDTO of(DesireGrowth desire) {
        DesireDTO dto = new DesireDTO();

        dto.id = desire.getId();
        dto.title = desire.getTitle();
        dto.content = desire.getContent();
        dto.sortNum = desire.getSortNum();

        return dto;
    }
}
