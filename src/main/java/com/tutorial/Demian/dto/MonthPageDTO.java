package com.tutorial.Demian.dto;

import lombok.Data;

import java.util.List;

@Data
public class MonthPageDTO {
    private DesireDTO desire;
    private List<MonthJobDTO> months;
}
