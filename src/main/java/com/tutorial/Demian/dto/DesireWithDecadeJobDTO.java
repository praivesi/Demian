package com.tutorial.Demian.dto;

import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;

import java.util.ArrayList;
import java.util.List;

public class DesireWithDecadeJobDTO {
    private DesireDTO desireDTO;
    private List<DecadeJobDTO> decadeJobDTOs = new ArrayList<>();

    public DesireDTO getDesireDTO() {
        return desireDTO;
    }

    public void setDesireDTO(DesireDTO desireDTO) {
        this.desireDTO = desireDTO;
    }

    public List<DecadeJobDTO> getDecadeJobDTOs() {
        return decadeJobDTOs;
    }

    public void setDecadeJobDTOs(List<DecadeJobDTO> decadeJobDTOs) {
        this.decadeJobDTOs = decadeJobDTOs;
    }

    public static DesireWithDecadeJobDTO of(Desire desire, List<DecadeJob> decadeJobs){
        DesireWithDecadeJobDTO dto = new DesireWithDecadeJobDTO();

        dto.desireDTO = DesireDTO.of(desire);

        for(int i = 0; i < decadeJobs.size(); i++) {
            dto.decadeJobDTOs.add(DecadeJobDTO.of(decadeJobs.get(i)));
        }

        return dto;
    }
}
