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
public class DayJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;
    private Timestamp fromTime;
    private Timestamp toTime;

    @ManyToOne
    @JoinColumn(name = "week_job_id")
    @JsonIgnore
    private WeekJob weekJob;

    public DayJob(){}

    public DayJob(String _title, String _content, Timestamp _from, Timestamp _to, WeekJob _weekJob) {
        this.title = _title;
        this.content = _content;
        this.fromTime = _from;
        this.toTime = _to;
        this.weekJob = _weekJob;
    }
}
