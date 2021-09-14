package com.tutorial.Demian.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class MonthGrowth {
    public final static long MONTH_JOB_DEFAULT_ID = -1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;

    private int yearNumber;

    private  int monthNumber; // 0 (January) ~ 11 (December)

    @ManyToOne
    @JoinColumn(name = "desire_id")
    @JsonIgnore
    private Desire desire;

    public MonthGrowth() {
        this.id = MONTH_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.yearNumber = 0;
        this.monthNumber = 0;
        this.desire = null;
    }

    public MonthGrowth(String _title, String _content, int _yearNumber, int _monthNumber, Desire _desire) {
        this.title = _title;
        this.content = _content;
        this.yearNumber = _yearNumber;
        this.monthNumber = _monthNumber;
        this.desire = _desire;
    }
}
