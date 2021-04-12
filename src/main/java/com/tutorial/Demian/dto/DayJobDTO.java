package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.DayJob;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DayJobDTO {
    private Long id;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    public static DayJobDTO of(DayJob day) {
        DayJobDTO dto = new DayJobDTO();

        dto.id = day.getId();
        dto.title = day.getTitle();
        dto.content = day.getContent();
        dto.fromTime = day.getFromTime();
        dto.toTime = day.getToTime();

        return dto;
    }
}
