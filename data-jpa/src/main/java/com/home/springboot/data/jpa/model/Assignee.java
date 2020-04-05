package com.home.springboot.data.jpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Assignee {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;

    @ManyToOne
//    @JoinColumn(name = "projectId")
    private Project project;

}
