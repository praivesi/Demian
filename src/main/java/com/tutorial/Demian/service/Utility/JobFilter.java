package com.tutorial.Demian.service.Utility;

import java.util.*;

import com.tutorial.Demian.dto.DecadeGrowthDTO;
import com.tutorial.Demian.dto.MonthGrowthDTO;
import com.tutorial.Demian.dto.YearGrowthDTO;
import com.tutorial.Demian.model.DecadeGrowth;
import com.tutorial.Demian.model.MonthGrowth;
import com.tutorial.Demian.model.YearGrowth;

public class JobFilter {
    public static List<DecadeGrowthDTO> decadeFilter(List<DecadeGrowth> entireDecadeGrowths, Date startDate, int decadeCount) {
        List<DecadeGrowthDTO> pickedDecades = new ArrayList<>();

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

                if (filterStartCal.get(Calendar.YEAR) <= decadeGrowth.getDecadeNumber() &&
                        decadeGrowth.getDecadeNumber() <= filterEndCal.get(Calendar.YEAR)) {
                    matchedJob = decadeGrowth;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedDecades.add(new DecadeGrowthDTO());
            } else {
                pickedDecades.add(DecadeGrowthDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedDecades;
    }

    public static List<YearGrowthDTO> yearFilter(List<YearGrowth> entireYearGrowths, Date startDate, int yearCount) {
        List<YearGrowthDTO> pickedYears = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), 0, 1, 0, 0, 0);

        for (int i = 0; i < yearCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.YEAR, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            YearGrowth matchedJob = null;

            Calendar filterStartCal = new GregorianCalendar();
            filterStartCal.setTime(filterStart);

            Calendar filterEndCal = new GregorianCalendar();
            filterEndCal.setTime(filterEnd);

            for (YearGrowth yearGrowth : entireYearGrowths) {
                if (filterStartCal.get(Calendar.YEAR) <= yearGrowth.getYearNumber() &&
                        yearGrowth.getYearNumber() <= filterEndCal.get(Calendar.YEAR)) {
                    matchedJob = yearGrowth;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedYears.add(new YearGrowthDTO());
            } else {
                pickedYears.add(YearGrowthDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedYears;
    }

    public static List<MonthGrowthDTO> monthFilter(List<MonthGrowth> entireMonthGrowths, Date startDate, int monthCount) {
        List<MonthGrowthDTO> pickedMonths = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);

        for (int i = 0; i < monthCount; i++) {
            Date filterStart = cal.getTime();

            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.SECOND, -1);
            Date filterEnd = cal.getTime();

            MonthGrowth matchedJob = null;

            Calendar filterStartCal = new GregorianCalendar();
            filterStartCal.setTime(filterStart);

            Calendar filterEndCal = new GregorianCalendar();
            filterEndCal.setTime(filterEnd);

            for (MonthGrowth monthGrowth : entireMonthGrowths) {
                if (filterStartCal.get(Calendar.YEAR) <= monthGrowth.getYearNumber() &&
                        monthGrowth.getYearNumber() <= filterEndCal.get(Calendar.YEAR) &&
                        filterStartCal.get(Calendar.MONTH) <= monthGrowth.getMonthNumber() &&
                        monthGrowth.getMonthNumber() <= filterEndCal.get(Calendar.MONTH)) {
                    matchedJob = monthGrowth;
                    break;
                }
            }

            if (matchedJob == null) {
                pickedMonths.add(new MonthGrowthDTO());
            } else {
                pickedMonths.add(MonthGrowthDTO.of(matchedJob));
            }

            cal.add(Calendar.SECOND, 1);
        }

        return pickedMonths;
    }
}