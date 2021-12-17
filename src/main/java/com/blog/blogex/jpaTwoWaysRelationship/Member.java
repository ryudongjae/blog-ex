package com.blog.blogex.jpaTwoWaysRelationship;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
