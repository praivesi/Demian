package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DesireWithDecadeJobDTO {
    private DesireDTO desireDTO;
    private List<DecadeJobDTO> decadeJobDTOs = new ArrayList<>();

    public static DesireWithDecadeJobDTO of(Desire desire, List<DecadeJob> decadeJobs){
        DesireWithDecadeJobDTO dto = new DesireWithDecadeJobDTO();

        dto.desireDTO = DesireDTO.of(desire);

        for(int i = 0; i < decadeJobs.size(); i++) {
            dto.decadeJobDTOs.add(DecadeJobDTO.of(decadeJobs.get(i)));
        }

        return dto;
    }
}
