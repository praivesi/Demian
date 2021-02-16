package com.tutorial.ohDiaraySpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private JOB_TYPE jobType;

    @NotNull
    private String title;

    private String content;
    private Timestamp from;
    private Timestamp to;

    @OneToOne
    @JoinColumn(name = "parent_job_id")
    private Job parentJob;

    @ManyToOne
    @JoinColumn(name = "desire_id")
    private Desire desire;

    public enum JOB_TYPE {
        DECADE,
        YEAR,
        MONTH,
        WEEK,
        DAY
    }
}



