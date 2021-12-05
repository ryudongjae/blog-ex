package com.blog.blogex.junit5;

import org.junit.jupiter.api.*;

class TestCode_1 {

    private final Calculator calculator = new Calculator();

    @BeforeEach
    public void setUp(){
    }

    @AfterEach
    public void delete(){
    }


    @Test
    @Timeout(value = 2)
    void test_1()throws Exception{
       //given

       //when

       //then


   }
}







