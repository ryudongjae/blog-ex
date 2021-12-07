package com.blog.blogex.assertj;

import lombok.Data;

@Data
public class Human {

    private String name;

    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
