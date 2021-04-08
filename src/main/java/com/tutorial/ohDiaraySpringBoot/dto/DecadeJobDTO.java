package com.tutorial.ohDiaraySpringBoot.dto;

import com.tutorial.ohDiaraySpringBoot.model.DecadeJob;
import lombok.Data;

import java.util.Date;

@Data
public class DecadeJobDTO {
    private Long id;
    private String title;
    private String content;
    private Date fromTime;
    private Date toTime;

    public static DecadeJobDTO of(DecadeJob decade){
        DecadeJobDTO dto = new DecadeJobDTO();

        dto.id = decade.getId();
        dto.title = decade.getTitle();
        dto.content = decade.getContent();
        dto.fromTime = decade.getFromTime();
        dto.toTime = decade.getToTime();

        return dto;
    }
}
