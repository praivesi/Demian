package com.tutorial.Demian.dto;

import lombok.Data;

import java.util.List;

@Data
public class DecadeNewDTO {
    private DesireDTO desireDTO;
    private List<DecadeJobDTO> decadeDTOsSortByTime;
}
