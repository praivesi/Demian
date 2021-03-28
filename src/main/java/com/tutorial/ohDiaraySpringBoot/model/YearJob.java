package com.tutorial.ohDiaraySpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class YearJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;
    private Date fromTime;
    private Date toTime;

    @ManyToOne
    @JoinColumn(name = "decade_job_id")
    @JsonIgnore
    private DecadeJob decadeJob;

    @OneToMany(mappedBy = "yearJob", fetch = FetchType.LAZY)
    private List<MonthJob> monthJobs = new ArrayList<>();

    public YearJob(){}

    public YearJob(String _title, String _content, Date _from, Date _to, DecadeJob _decadeJob) {
        this.title = _title;
        this.content = _content;
        this.fromTime = _from;
        this.toTime = _to;
        this.decadeJob = _decadeJob;
    }
}
