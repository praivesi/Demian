package com.tutorial.ohDiaraySpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
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

    @OneToMany(mappedBy = "desire", fetch = FetchType.LAZY)
    private List<DecadeJob> decadeJobs = new ArrayList<>();

    public Desire(){}

    public Desire(String _title, String _content, Long _sortNum){
        this.title = _title;
        this.content = _content;
        this.sortNum = _sortNum;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
