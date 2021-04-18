package com.tutorial.Demian.service.Utility;

import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.model.DecadeJob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JobFilter {
    public static List<DecadeJobDTO> decadeFilter(List<DecadeJob> entireDecades, Date startDate, int decadeCount) {
        List<DecadeJobDTO> pickedDecades = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        int startYear = cal.get(Calendar.YEAR);
        cal.set(startYear - startYear % 10, 0, 1, 0, 0, 0);

        for (int i = 0; i < decadeCount; i++) {
            Date startDecade = cal.getTime();

            cal.add(Calendar.YEAR, 10);
            cal.add(Calendar.SECOND, -1);
            Date endDecade = cal.getTime();

            DecadeJob matchedJob = null;
            for (DecadeJob decadeJob : entireDecades) {
                if (startDecade.getTime() <= decadeJob.getFromTime().getTime() &&
                        decadeJob.getToTime().getTime() <= endDecade.getTime()) {
                    matchedJob = decadeJob;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedDecades.add(new DecadeJobDTO());
            } else {
                pickedDecades.add(DecadeJobDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedDecades;
    }
}