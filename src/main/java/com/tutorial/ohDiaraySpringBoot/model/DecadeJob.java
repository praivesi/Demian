package com.tutorial.ohDiaraySpringBoot.model;

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

    @OneToMany(mappedBy = "decadeJob", fetch = FetchType.LAZY)
    private List<YearJob> yearJobs = new ArrayList<>();

    public DecadeJob(){

    }

    public DecadeJob(String _title, String _content, Date _fromTime, Date _toTime, Desire _desire) {
        this.title = _title;
        this.content = _content;
        this.fromTime = _fromTime;
        this.toTime = _toTime;
        this.desire = _desire;
    }
}