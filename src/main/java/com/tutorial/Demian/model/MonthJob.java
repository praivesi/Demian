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
    public final static long MONTH_JOB_DEFAULT_ID = -1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;
    private Date fromTime;
    private Date toTime;

    @ManyToOne
    @JoinColumn(name = "desire_id")
    @JsonIgnore
    private Desire desire;

    public MonthJob() {
        this.id = MONTH_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.fromTime = null;
        this.toTime = null;
        this.desire = null;
    }

    public MonthJob(String _title, String _content, Date _from, Date _to, Desire _desire) {
        this.title = _title;
        this.content = _content;
        this.fromTime = _from;
        this.toTime = _to;
        this.desire = _desire;
    }
}
