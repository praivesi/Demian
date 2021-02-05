package com.tutorial.ohDiaraySpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2글자 이상 30 글자 이하여야 합니다.")
    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id"/*, referencedColumnName = "id"*/)
    @JsonIgnore
    private  User user;
}
