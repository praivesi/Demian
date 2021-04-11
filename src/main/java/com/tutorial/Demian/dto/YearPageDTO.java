package com.tutorial.Demian.dto;

import lombok.Data;

import java.util.List;

@Data
public class YearPageDTO {
    private DesireDTO desire;
    private List<YearJobDTO> years;
}
