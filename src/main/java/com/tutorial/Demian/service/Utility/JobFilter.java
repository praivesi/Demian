package com.tutorial.Demian.service.Utility;

import java.util.*;

import com.tutorial.Demian.dto.DecadeDTO;
import com.tutorial.Demian.dto.MonthDTO;
import com.tutorial.Demian.dto.YearDTO;
import com.tutorial.Demian.model.DecadeGrowth;
import com.tutorial.Demian.model.MonthGrowth;
import com.tutorial.Demian.model.YearGrowth;

public class JobFilter {
    public static List<DecadeDTO> decadeFilter(List<DecadeGrowth> entireDecadeGrowths, Date startDate, int decadeCount) {
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

            DecadeGrowth matchedJob = null;

            Calendar filterStartCal = new GregorianCalendar();
            filterStartCal.setTime(filterStart);

            Calendar filterEndCal = new GregorianCalendar();
            filterEndCal.setTime(filterEnd);

            for (DecadeGrowth decadeGrowth : entireDecadeGrowths) {

                if(filterStartCal.get(Calendar.YEAR) <= decadeGrowth.getDecadeNumber() &&
                    decadeGrowth.getDecadeNumber() <= filterEndCal.get(Calendar.YEAR)){
                    matchedJob = decadeGrowth;
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

    public static List<YearDTO> yearFilter(List<YearGrowth> entireYearGrowths, Date startDate, int yearCount) {
        List<YearDTO> pickedYears = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);

        for (int i = 0; i < yearCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.YEAR, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            YearGrowth matchedJob = null;
            for (YearGrowth yearGrowth : entireYearGrowths) {
                if (filterStart.getTime() <= yearGrowth.getFromTime().getTime() &&
                        yearGrowth.getToTime().getTime() <= filterEnd.getTime()) {
                    matchedJob = yearGrowth;
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

    public static List<MonthDTO> monthFilter(List<MonthGrowth> entireMonthGrowths, Date startDate, int monthCount) {
        List<MonthDTO> pickedMonths = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);

        for (int i = 0; i < monthCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            MonthGrowth matchedJob = null;
            for (MonthGrowth monthGrowth : entireMonthGrowths) {
                if (filterStart.getTime() <= monthGrowth.getFromTime().getTime() &&
                        monthGrowth.getToTime().getTime() <= filterEnd.getTime()) {
                    matchedJob = monthGrowth;
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