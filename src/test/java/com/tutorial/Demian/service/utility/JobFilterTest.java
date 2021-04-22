package com.tutorial.Demian.service.utility;

import com.tutorial.Demian.dto.DecadeJobDTO;
import com.tutorial.Demian.dto.MonthJobDTO;
import com.tutorial.Demian.dto.YearJobDTO;
import com.tutorial.Demian.model.DecadeJob;
import com.tutorial.Demian.model.Desire;
import com.tutorial.Demian.model.MonthJob;
import com.tutorial.Demian.model.YearJob;
import com.tutorial.Demian.service.Utility.JobFilter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JobFilterTest {
    private JobFilter jobFilter;

    @BeforeEach
    void setup() {
        this.jobFilter = new JobFilter();
    }

    @Test
    void decadeJobFilter_succeed() {
        // given
        List<DecadeJob> entireDecades = new ArrayList<>();
        Calendar startCal = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.MONTH, 0);

        for (long i = 0; i < 10; i++) {
            DecadeJob decadeJob = new DecadeJob();
            decadeJob.setId(i);
            decadeJob.setFromTime(cal.getTime());

            cal.add(Calendar.MONTH, 1);
            decadeJob.setToTime(cal.getTime());

            Desire desire = new Desire();
            desire.setId(i);
            decadeJob.setDesire(desire);

            entireDecades.add(decadeJob);

            cal.add(Calendar.YEAR, 10);
            cal.add(Calendar.MONTH, -1);
        }

        startCal.set(Calendar.YEAR, 2020);

        // when
        List<DecadeJobDTO> pickedDecades
                = this.jobFilter.decadeFilter(entireDecades, startCal.getTime(), 5);

        // then
        Assertions.assertThat(pickedDecades.size()).isEqualTo(5);
        Assertions.assertThat(pickedDecades).element(0)
                .matches(dto -> dto.getFromTime().toString().contains("2020"));
        Assertions.assertThat(pickedDecades).element(1)
                .matches(dto -> dto.getFromTime().toString().contains("2030"));
        Assertions.assertThat(pickedDecades).element(2)
                .matches(dto -> dto.getFromTime().toString().contains("2040"));
        Assertions.assertThat(pickedDecades).element(3)
                .matches(dto -> dto.getFromTime().toString().contains("2050"));
        Assertions.assertThat(pickedDecades).element(4)
                .matches(dto -> dto.getFromTime().toString().contains("2060"));
    }

    @Test
    void yearJobFilter_succeed() {
        // given
        List<YearJob> entireYears = new ArrayList<>();
        Calendar startCal = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.MONTH, 0);

        for (long i = 0; i < 10; i++) {
            YearJob yearJob = new YearJob();
            yearJob.setId(i);
            yearJob.setFromTime(cal.getTime());

            cal.add(Calendar.MONTH, 1);
            yearJob.setToTime(cal.getTime());

            Desire desire = new Desire();
            desire.setId(i);
            yearJob.setDesire(desire);

            entireYears.add(yearJob);

            cal.add(Calendar.YEAR, 1);
            cal.add(Calendar.MONTH, -1);
        }

        startCal.set(Calendar.YEAR, 2020);

        // when
        List<YearJobDTO> pickedYears
                = this.jobFilter.yearFilter(entireYears, startCal.getTime(), 5);

        // then
        Assertions.assertThat(pickedYears.size()).isEqualTo(5);
        Assertions.assertThat(pickedYears).element(0)
                .matches(dto -> dto.getFromTime().toString().contains("2020"));
        Assertions.assertThat(pickedYears).element(1)
                .matches(dto -> dto.getFromTime().toString().contains("2021"));
        Assertions.assertThat(pickedYears).element(2)
                .matches(dto -> dto.getFromTime().toString().contains("2022"));
        Assertions.assertThat(pickedYears).element(3)
                .matches(dto -> dto.getFromTime().toString().contains("2023"));
        Assertions.assertThat(pickedYears).element(4)
                .matches(dto -> dto.getFromTime().toString().contains("2024"));
    }

//    @Test
//    void monthJobFilter_succeed() {
//        // given
//        List<MonthJob> entireMonths = new ArrayList<>();
//        Calendar startCal = Calendar.getInstance();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2020);
//        cal.set(Calendar.MONTH, 0);
//
//        for (long i = 0; i < 10; i++) {
//            MonthJob monthJob = new MonthJob();
//            monthJob.setId(i);
//            monthJob.setFromTime(cal.getTime());
//
//            cal.add(Calendar.DAY_OF_MONTH, 1);
//            monthJob.setToTime(cal.getTime());
//
//            Desire desire = new Desire();
//            desire.setId(i);
//            monthJob.setDesire(desire);
//
//            entireMonths.add(monthJob);
//
//            cal.add(Calendar.MONTH, 1);
//            cal.add(Calendar.DAY_OF_MONTH, -1);
//        }
//
//        startCal.set(Calendar.YEAR, 2020);
//
//        // when
//        List<MonthJobDTO> pickedMonths
//                = this.jobFilter.monthFilter(entireMonths, startCal.getTime(), 5);
//
//        // then
//        Assertions.assertThat(pickedMonths.size()).isEqualTo(5);
//        Assertions.assertThat(pickedMonths).element(0)
//                .matches(dto -> dto.getFromTime().toString().contains("2020-01"));
//        Assertions.assertThat(pickedMonths).element(1)
//                .matches(dto -> dto.getFromTime().toString().contains("2020-02"));
//        Assertions.assertThat(pickedMonths).element(2)
//                .matches(dto -> dto.getFromTime().toString().contains("2020-03"));
//        Assertions.assertThat(pickedMonths).element(3)
//                .matches(dto -> dto.getFromTime().toString().contains("2020-04"));
//        Assertions.assertThat(pickedMonths).element(4)
//                .matches(dto -> dto.getFromTime().toString().contains("2020-05"));
//    }
}
