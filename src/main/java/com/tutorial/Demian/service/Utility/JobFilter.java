package com.tutorial.Demian.service.Utility;

import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.dto.MonthJobDTO;
import com.tutorial.Demian.dto.YearJobDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.MonthJob;
import com.tutorial.Demian.model.YearJob;

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
            Date filterStart = cal.getTime();

            cal.add(Calendar.YEAR, 10);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            DecadeJob matchedJob = null;
            for (DecadeJob decadeJob : entireDecades) {
                if (filterStart.getTime() <= decadeJob.getFromTime().getTime() &&
                        decadeJob.getToTime().getTime() <= filterEnd.getTime()) {
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

    public static List<YearJobDTO> yearFilter(List<YearJob> entireYears, Date startDate, int yearCount) {
        List<YearJobDTO> pickedYears = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);

        for (int i = 0; i < yearCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.YEAR, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            YearJob matchedJob = null;
            for (YearJob yearJob : entireYears) {
                if (filterStart.getTime() <= yearJob.getFromTime().getTime() &&
                        yearJob.getToTime().getTime() <= filterEnd.getTime()) {
                    matchedJob = yearJob;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedYears.add(new YearJobDTO());
            } else {
                pickedYears.add(YearJobDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedYears;
    }

    public static List<MonthJobDTO> monthFilter(List<MonthJob> entireMonths, Date startDate, int monthCount) {
        List<MonthJobDTO> pickedMonths = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);

        for (int i = 0; i < monthCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            MonthJob matchedJob = null;
            for (MonthJob monthJob : entireMonths) {
                if (filterStart.getTime() <= monthJob.getFromTime().getTime() &&
                        monthJob.getToTime().getTime() <= filterEnd.getTime()) {
                    matchedJob = monthJob;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedMonths.add(new MonthJobDTO());
            } else {
                pickedMonths.add(MonthJobDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedMonths;
    }
}