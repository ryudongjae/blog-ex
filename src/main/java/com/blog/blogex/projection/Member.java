package com.blog.blogex.projection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id@GeneratedValue
    private Long id;
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
