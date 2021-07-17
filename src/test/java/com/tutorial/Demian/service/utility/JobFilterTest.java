package com.tutorial.Demian.service.utility;

public class JobFilterTest {
//    private JobFilter jobFilter;
//
//    @BeforeEach
//    void setup() {
//        this.jobFilter = new JobFilter();
//    }
//
//    @Test
//    void decadeJobFilter_succeed() {
//        // given
//        List<Decade> entireDecades = new ArrayList<>();
//        Calendar startCal = Calendar.getInstance();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2000);
//        cal.set(Calendar.MONTH, 0);
//
//        for (long i = 0; i < 10; i++) {
//            Decade decade = new Decade();
//            decade.setId(i);
//            decade.setFromTime(cal.getTime());
//
//            cal.add(Calendar.MONTH, 1);
//            decade.setToTime(cal.getTime());
//
//            Desire desire = new Desire();
//            desire.setId(i);
//            decade.setDesire(desire);
//
//            entireDecades.add(decade);
//
//            cal.add(Calendar.YEAR, 10);
//            cal.add(Calendar.MONTH, -1);
//        }
//
//        startCal.set(Calendar.YEAR, 2020);
//
//        // when
//        List<DecadeDTO> pickedDecades
//                = this.jobFilter.decadeFilter(entireDecades, startCal.getTime(), 5);
//
//        // then
//        Assertions.assertThat(pickedDecades.size()).isEqualTo(5);
//        Assertions.assertThat(pickedDecades).element(0)
//                .matches(dto -> dto.getFromTime().toString().contains("2020"));
//        Assertions.assertThat(pickedDecades).element(1)
//                .matches(dto -> dto.getFromTime().toString().contains("2030"));
//        Assertions.assertThat(pickedDecades).element(2)
//                .matches(dto -> dto.getFromTime().toString().contains("2040"));
//        Assertions.assertThat(pickedDecades).element(3)
//                .matches(dto -> dto.getFromTime().toString().contains("2050"));
//        Assertions.assertThat(pickedDecades).element(4)
//                .matches(dto -> dto.getFromTime().toString().contains("2060"));
//    }
//
//    @Test
//    void yearJobFilter_succeed() {
//        // given
//        List<Year> entireYears = new ArrayList<>();
//        Calendar startCal = Calendar.getInstance();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2020);
//        cal.set(Calendar.MONTH, 0);
//
//        for (long i = 0; i < 10; i++) {
//            Year year = new Year();
//            year.setId(i);
//            year.setFromTime(cal.getTime());
//
//            cal.add(Calendar.MONTH, 1);
//            year.setToTime(cal.getTime());
//
//            Desire desire = new Desire();
//            desire.setId(i);
//            year.setDesire(desire);
//
//            entireYears.add(year);
//
//            cal.add(Calendar.YEAR, 1);
//            cal.add(Calendar.MONTH, -1);
//        }
//
//        startCal.set(Calendar.YEAR, 2020);
//
//        // when
//        List<YearDTO> pickedYears
//                = this.jobFilter.yearFilter(entireYears, startCal.getTime(), 5);
//
//        // then
//        Assertions.assertThat(pickedYears.size()).isEqualTo(5);
//        Assertions.assertThat(pickedYears).element(0)
//                .matches(dto -> dto.getFromTime().toString().contains("2020"));
//        Assertions.assertThat(pickedYears).element(1)
//                .matches(dto -> dto.getFromTime().toString().contains("2021"));
//        Assertions.assertThat(pickedYears).element(2)
//                .matches(dto -> dto.getFromTime().toString().contains("2022"));
//        Assertions.assertThat(pickedYears).element(3)
//                .matches(dto -> dto.getFromTime().toString().contains("2023"));
//        Assertions.assertThat(pickedYears).element(4)
//                .matches(dto -> dto.getFromTime().toString().contains("2024"));
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {0, 1, 2, 3, 4})
//    void monthJobFilter_succeed(int monthNum) {
//        // given
//        List<Month> entireMonths = new ArrayList<>();
//        Calendar startCal = Calendar.getInstance();
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 2020);
//        cal.set(Calendar.MONTH, 0);
//
//        for (long i = 0; i < 10; i++) {
//            Month month = new Month();
//            month.setId(i);
//            month.setFromTime(cal.getTime());
//
//            cal.add(Calendar.DAY_OF_MONTH, 1);
//            month.setToTime(cal.getTime());
//
//            Desire desire = new Desire();
//            desire.setId(i);
//            month.setDesire(desire);
//
//            entireMonths.add(month);
//
//            cal.add(Calendar.MONTH, 1);
//            cal.add(Calendar.DAY_OF_MONTH, -1);
//        }
//
//        startCal.set(Calendar.YEAR, 2020);
//        startCal.set(Calendar.MONTH, 0);
//
//        // when
//        List<MonthDTO> pickedMonths
//                = this.jobFilter.monthFilter(entireMonths, startCal.getTime(), 5);
//
//        // then
//        Assertions.assertThat(pickedMonths.size()).isEqualTo(5);
//        Assertions.assertThat(pickedMonths).element(monthNum)
//                .matches(dto -> {
//                    Calendar tmpCal = Calendar.getInstance();
//                    tmpCal.setTime(dto.getFromTime());
//                    return tmpCal.get(Calendar.MONTH) == monthNum;
//                });
//    }
}
