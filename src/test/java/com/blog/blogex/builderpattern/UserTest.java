package com.blog.blogex.builderpattern;

import org.junit.jupiter.api.Test;


class UserTest {

    @Test
    void user()throws Exception{
        UserBuilder user = UserBuilder.builder()
                .email("rrr11@naver.com")
                .name("KK")
                .age(14)
                .build();
    }

//    @Test
//    void Setter()throws Exception{
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("rrr11@naver.com");
//        user.setName("KK");
//        user.setPassword("l12313123");
//        user.setAge(23);
//
//    }
}