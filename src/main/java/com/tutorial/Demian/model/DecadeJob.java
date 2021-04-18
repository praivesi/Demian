package com.tutorial.Demian.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class DecadeJob {
    public final static long DECADE_JOB_DEFAULT_ID = -1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date fromTime;

    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date toTime;

    @ManyToOne
    @JoinColumn(name = "desire_id")
    @JsonIgnore
    private Desire desire;

    public DecadeJob(){
        this.id = DECADE_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.fromTime = null;
        this.toTime = null;
        this.desire = null;
    }

    public DecadeJob(String _title, String _content, Date _fromTime, Date _toTime, Desire _desire) {
        this.id = DECADE_JOB_DEFAULT_ID;
        this.title = _title;
        this.content = _content;
        this.fromTime = _fromTime;
        this.toTime = _toTime;
        this.desire = _desire;
    }
}