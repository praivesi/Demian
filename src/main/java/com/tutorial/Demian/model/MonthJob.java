package com.tutorial.Demian.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class MonthJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;
    private Date fromTime;
    private Date toTime;

    @ManyToOne
    @JoinColumn(name = "year_job_id")
    @JsonIgnore
    private YearJob yearJob;

    @OneToMany(mappedBy = "monthJob", fetch = FetchType.LAZY)
    private List<WeekJob> weekJobs = new ArrayList<>();

    public MonthJob(){}

    public MonthJob(String _title, String _content, Date _from, Date _to, YearJob _yearJob) {
        this.title = _title;
        this.content = _content;
        this.fromTime = _from;
        this.toTime = _to;
        this.yearJob = _yearJob;
    }
}
