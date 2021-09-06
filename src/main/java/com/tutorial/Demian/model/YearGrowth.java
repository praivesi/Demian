package com.tutorial.Demian.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class YearGrowth {
    public final static long YEAR_JOB_DEFAULT_ID = -1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;

    private int yearNumber;

    @ManyToOne
    @JoinColumn(name = "desire_id")
    @JsonIgnore
    private Desire desire;

    public YearGrowth() {
        this.id = YEAR_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.yearNumber = 0;
        this.desire = null;
    }

    public YearGrowth(String _title, String _content, int _yearNumber, Desire _desire) {
        this.id = YEAR_JOB_DEFAULT_ID;
        this.title = _title;
        this.content = _content;
        this.yearNumber = _yearNumber;
        this.desire = _desire;
    }
}
