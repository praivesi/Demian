package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.MonthJob;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MonthJobDTO {
    private Long id;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    public static MonthJobDTO of(MonthJob month) {
        MonthJobDTO dto = new MonthJobDTO();

        dto.id = month.getId();
        dto.title = month.getTitle();
        dto.content = month.getContent();
        dto.fromTime = month.getFromTime();
        dto.toTime = month.getToTime();

        return dto;
    }
}
