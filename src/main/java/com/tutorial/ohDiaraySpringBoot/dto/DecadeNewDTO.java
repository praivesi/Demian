package com.tutorial.ohDiaraySpringBoot.dto;

import lombok.Data;

import java.util.List;

@Data
public class DecadeNewDTO {
    private DesireDTO desireDTO;
    private List<DecadeJobDTO> decadeDTOsSortByTime;
}
