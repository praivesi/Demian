package com.tutorial.ohDiaraySpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Controller;

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
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;
    @Column(name = "sortNum")
    private Long sortNum;
    @Column(name = "from_time")
    private Timestamp fromTime;
    @Column(name = "to_time")
    private Timestamp toTime;

    @OneToMany(mappedBy = "desire", fetch = FetchType.LAZY)
    private List<DecadeJob> decadeJobs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
