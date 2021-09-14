package com.tutorial.Demian.dto;

import lombok.Data;

import java.util.Date;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String content;
    private Long parentId;
    private int jobType; // 0 - Decade, 1 - Year, 2 - Month, 3 - Week, 4 - Day
    private int decadeNumber;
    private int yearNumber;
    private int monthNumber;
}
