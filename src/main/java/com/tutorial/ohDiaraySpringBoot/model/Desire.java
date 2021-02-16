package com.tutorial.ohDiaraySpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Desire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;
    private String content;
    private Long sortNum;
    private Timestamp from;
    private Timestamp to;

    @OneToMany(mappedBy = "desire", fetch = FetchType.LAZY)
    private List<Job> jobs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
