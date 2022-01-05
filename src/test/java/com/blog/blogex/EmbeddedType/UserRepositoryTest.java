package com.blog.blogex.EmbeddedType;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    void embeddedType()throws Exception{
        //given
        User user = new User("KIM",new Address("seoul","guro"),new Address("seoul","mapo"),new Period());
        //when
        User save = userRepository.save(user);
        //then
        Assertions.assertThat(user.getHomeAddress().getCity()).isEqualTo(save.getHomeAddress().getCity());
        Assertions.assertThat(user.getWorkAddress().getCity()).isEqualTo(save.getWorkAddress().getCity());

    }

}