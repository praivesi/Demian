package com.tutorial.Demian.service.Utility;

import com.tutorial.Demian.dto.YearPageDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeHeaderCalculator {
    public static List<String> getDecadeTimeHeaders(Calendar startCal, int timeHeaderCount) {
        List<String> timeHeaders = new ArrayList<>();

        Calendar beginCal = (Calendar) startCal.clone();
        Calendar endCal = (Calendar) startCal.clone();

        for (int i = 0; i < timeHeaderCount; i++) {
            endCal.add(Calendar.YEAR, 9);
            timeHeaders.add(String.format(
                    "%s ~ %s",
                    new SimpleDateFormat("yyyy").format(beginCal.getTime()),
                    new SimpleDateFormat("yyyy").format(endCal.getTime())));

            beginCal.add(Calendar.YEAR, 10);
            endCal.add(Calendar.YEAR, 1);
        }

        return timeHeaders;
    }

    public static List<String> getYearTimeHeaders(Calendar startCal, int timeHeaderCount) {
        List<String> timeHeaders = new ArrayList<>();

        for (int i = 0; i < timeHeaderCount; i++) {
            timeHeaders.add(new SimpleDateFormat("yyyy").format(startCal.getTime()));
            startCal.add(Calendar.YEAR, 1);
        }

        return timeHeaders;
    }

    public static List<String> getMonthTimeHeaders(Calendar startCal, int timeHeaderCount) {
        List<String> timeHeaders = new ArrayList<>();

        for (int i = 0; i < timeHeaderCount; i++) {
            timeHeaders.add(new SimpleDateFormat("yyyy-MM").format(startCal.getTime()));
            startCal.add(Calendar.MONTH, 1);
        }

        return timeHeaders;
    }
}
