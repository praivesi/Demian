package com.tutorial.Demian.service.utility;

import com.tutorial.Demian.service.Utility.TimeHeaderCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

public class TimeHeaderCalculatorTest {

//    private TimeHeaderCalculator timeHeaderCalculator;
//
//    @BeforeEach
//    void setup() {
//        this.timeHeaderCalculator = new TimeHeaderCalculator();
//    }
//
//    @Test
//    @DisplayName("Decade Time Header 구하기 성공")
//    void getDecadeTimeHeaders_Succeed_NormalCase()
//    {
//        // given
//        Calendar startCal = Calendar.getInstance();
//        startCal.set(Calendar.YEAR, 2000);
//        int timeHeaderCount = 5;
//
//        // when
//        List<String> timeHeaders = this.timeHeaderCalculator.getDecadeTimeHeaders(startCal, timeHeaderCount);
//
//        // then
//        Assertions.assertThat(timeHeaders.size()).isEqualTo(timeHeaderCount);
//        Assertions.assertThat(timeHeaders.get(0)).isEqualTo("2000 ~ 2009");
//        Assertions.assertThat(timeHeaders.get(1)).isEqualTo("2010 ~ 2019");
//        Assertions.assertThat(timeHeaders.get(2)).isEqualTo("2020 ~ 2029");
//        Assertions.assertThat(timeHeaders.get(3)).isEqualTo("2030 ~ 2039");
//        Assertions.assertThat(timeHeaders.get(4)).isEqualTo("2040 ~ 2049");
//    }
//
//    @Test
//    @DisplayName("시작 년도가 10년 단위가 아닐 경우에도 Decade Time Header 구하기 성공")
//    void getDecadeTimeHeaders_Succeed_StartCalIsNotDividingBy10()
//    {
//        // given
//        Calendar startCal = Calendar.getInstance();
//        startCal.set(Calendar.YEAR, 2001);
//        int timeHeaderCount = 5;
//
//        // when
//        List<String> timeHeaders = this.timeHeaderCalculator.getDecadeTimeHeaders(startCal, timeHeaderCount);
//
//        // then
//        Assertions.assertThat(timeHeaders.size()).isEqualTo(timeHeaderCount);
//        Assertions.assertThat(timeHeaders.get(0)).isEqualTo("2000 ~ 2009");
//        Assertions.assertThat(timeHeaders.get(1)).isEqualTo("2010 ~ 2019");
//        Assertions.assertThat(timeHeaders.get(2)).isEqualTo("2020 ~ 2029");
//        Assertions.assertThat(timeHeaders.get(3)).isEqualTo("2030 ~ 2039");
//        Assertions.assertThat(timeHeaders.get(4)).isEqualTo("2040 ~ 2049");
//    }
//
//    @Test
//    @DisplayName("Year Time Header 구하기 성공")
//    void getYearTimeHeaders_Succeed_NormalCase()
//    {
//        // given
//        Calendar startCal = Calendar.getInstance();
//        startCal.set(Calendar.YEAR, 2000);
//        int timeHeaderCount = 5;
//
//        // when
//        List<String> timeHeaders = this.timeHeaderCalculator.getYearTimeHeaders(startCal, timeHeaderCount);
//
//        // then
//        Assertions.assertThat(timeHeaders.size()).isEqualTo(timeHeaderCount);
//        Assertions.assertThat(timeHeaders.get(0)).isEqualTo("2000");
//        Assertions.assertThat(timeHeaders.get(1)).isEqualTo("2001");
//        Assertions.assertThat(timeHeaders.get(2)).isEqualTo("2002");
//        Assertions.assertThat(timeHeaders.get(3)).isEqualTo("2003");
//        Assertions.assertThat(timeHeaders.get(4)).isEqualTo("2004");
//    }
//
//    @Test
//    @DisplayName("Month Time Header 구하기 성공")
//    void getMonthTimeHeaders_Succeed_NormalCase()
//    {
//        // given
//        Calendar startCal = Calendar.getInstance();
//        startCal.set(Calendar.YEAR, 2000);
//        startCal.set(Calendar.MONTH, 0);
//        int timeHeaderCount = 5;
//
//        // when
//        List<String> timeHeaders = this.timeHeaderCalculator.getMonthTimeHeaders(startCal, timeHeaderCount);
//
//        // then
//        Assertions.assertThat(timeHeaders.size()).isEqualTo(timeHeaderCount);
//        Assertions.assertThat(timeHeaders.get(0)).isEqualTo("2000-01");
//        Assertions.assertThat(timeHeaders.get(1)).isEqualTo("2000-02");
//        Assertions.assertThat(timeHeaders.get(2)).isEqualTo("2000-03");
//        Assertions.assertThat(timeHeaders.get(3)).isEqualTo("2000-04");
//        Assertions.assertThat(timeHeaders.get(4)).isEqualTo("2000-05");
//    }
}
