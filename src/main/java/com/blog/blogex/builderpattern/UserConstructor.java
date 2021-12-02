package com.blog.blogex.builderpattern;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserConstructor {
    private Long id;
    private String email;
    private String name;
    private String password;
    private int age;



    public UserConstructor(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public UserConstructor(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }
}
