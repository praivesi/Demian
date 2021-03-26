package com.tutorial.ohDiaraySpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class WeekJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;
    private Timestamp fromTime;
    private Timestamp toTime;

    @ManyToOne
    @JoinColumn(name = "month_job_id")
    @JsonIgnore
    private MonthJob monthJob;

    @OneToMany(mappedBy = "weekJob", fetch = FetchType.LAZY)
    private List<DayJob> dayJobs = new ArrayList<>();

    public WeekJob(){}

    public WeekJob(String _title, String _content, Timestamp _from, Timestamp _to, MonthJob _monthJob) {
        this.title = _title;
        this.content = _content;
        this.fromTime = _from;
        this.toTime = _to;
        this.monthJob = _monthJob;
    }
}
