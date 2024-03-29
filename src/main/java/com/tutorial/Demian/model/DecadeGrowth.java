package com.tutorial.Demian.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class DecadeGrowth {
    public final static long DECADE_JOB_DEFAULT_ID = -1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String content;

    private int decadeNumber;

    @ManyToOne
    @JoinColumn(name = "desire_id")
    @JsonIgnore
    private Desire desire;

    public DecadeGrowth() {
        this.id = DECADE_JOB_DEFAULT_ID;
        this.title = "";
        this.content = "";
        this.decadeNumber = 0;
        this.desire = null;
    }

    public DecadeGrowth(String _title, String _content, int _decadeNumber, Desire _desire) {
        this.id = DECADE_JOB_DEFAULT_ID;
        this.title = _title;
        this.content = _content;
        this.decadeNumber = _decadeNumber;
        this.desire = _desire;
    }
}