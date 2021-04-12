package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.WeekJob;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class WeekJobDTO {
    private Long id;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    public static WeekJobDTO of(WeekJob week){
        WeekJobDTO dto = new WeekJobDTO();

        dto.id = week.getId();
        dto.title = week.getTitle();
        dto.content = week.getContent();
        dto.fromTime = week.getFromTime();
        dto.toTime = week.getToTime();

        return dto;
    }
}
