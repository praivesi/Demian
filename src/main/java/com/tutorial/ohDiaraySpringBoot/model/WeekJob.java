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
    private Timestamp from;
    private Timestamp to;

    @ManyToOne
    @JoinColumn(name = "month_job_id")
    @JsonIgnore
    private MonthJob monthJob;

    @OneToMany(mappedBy = "weekJob", fetch = FetchType.LAZY)
    private List<DayJob> dayJobs = new ArrayList<>();
}
