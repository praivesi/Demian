package com.tutorial.Demian.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class DayJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;
    private Date fromTime;
    private Date toTime;

    @ManyToOne
    @JoinColumn(name = "week_job_id")
    @JsonIgnore
    private WeekJob weekJob;

    public DayJob(){}

    public DayJob(String _title, String _content, Date _from, Date _to, WeekJob _weekJob) {
        this.title = _title;
        this.content = _content;
        this.fromTime = _from;
        this.toTime = _to;
        this.weekJob = _weekJob;
    }
}
