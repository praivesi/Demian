package com.tutorial.Demian.service.Utility;

import com.tutorial.Demian.dto.DecadeDTO;
import com.tutorial.Demian.dto.MonthDTO;
import com.tutorial.Demian.dto.YearDTO;
import com.tutorial.Demian.model.Decade;
import com.tutorial.Demian.model.Month;
import com.tutorial.Demian.model.Year;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JobFilter {
    public static List<DecadeDTO> decadeFilter(List<Decade> entireDecades, Date startDate, int decadeCount) {
        List<DecadeDTO> pickedDecades = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        int startYear = cal.get(Calendar.YEAR);
        cal.set(startYear - startYear % 10, 0, 1, 0, 0, 0);

        for (int i = 0; i < decadeCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.YEAR, 10);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            Decade matchedJob = null;
            for (Decade decade : entireDecades) {
                if (filterStart.getTime() <= decade.getFromTime().getTime() &&
                        decade.getToTime().getTime() <= filterEnd.getTime()) {
                    matchedJob = decade;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedDecades.add(new DecadeDTO());
            } else {
                pickedDecades.add(DecadeDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedDecades;
    }

    public static List<YearDTO> yearFilter(List<Year> entireYears, Date startDate, int yearCount) {
        List<YearDTO> pickedYears = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);

        for (int i = 0; i < yearCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.YEAR, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            Year matchedJob = null;
            for (Year year : entireYears) {
                if (filterStart.getTime() <= year.getFromTime().getTime() &&
                        year.getToTime().getTime() <= filterEnd.getTime()) {
                    matchedJob = year;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedYears.add(new YearDTO());
            } else {
                pickedYears.add(com.tutorial.Demian.dto.YearDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedYears;
    }

    public static List<MonthDTO> monthFilter(List<Month> entireMonths, Date startDate, int monthCount) {
        List<MonthDTO> pickedMonths = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);

        for (int i = 0; i < monthCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            Month matchedJob = null;
            for (Month month : entireMonths) {
                if (filterStart.getTime() <= month.getFromTime().getTime() &&
                        month.getToTime().getTime() <= filterEnd.getTime()) {
                    matchedJob = month;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedMonths.add(new MonthDTO());
            } else {
                pickedMonths.add(MonthDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedMonths;
    }
}