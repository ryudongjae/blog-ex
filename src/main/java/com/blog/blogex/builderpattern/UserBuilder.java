package com.blog.blogex.builderpattern;


import lombok.Builder;
import lombok.Data;


public class UserBuilder {

    private Long id;
    private String email;
    private String name;
    private String password;
    private int age;

    @Builder
    public UserBuilder(Long id, String email, String name, String password, int age) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.age = age;
    }

}
