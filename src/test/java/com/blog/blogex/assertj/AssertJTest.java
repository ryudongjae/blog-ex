package com.blog.blogex.assertj;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.filter.NotInFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.notIn;
import static org.junit.jupiter.api.Assertions.*;

class AssertJTest {

    @Test
    void filter_test()throws Exception{

        List<Human> list = new ArrayList<>();
        Human k = new Human("Kim",22);
        Human a = new Human("Aim",23);
        Human p = new Human("Park",42);
        Human j = new Human("Jin",12);
        Human y = new Human("yun",32);

        list.add(k);
        list.add(a);
        list.add(p);
        list.add(j);
        list.add(y);

        Assertions.assertThat(list).filteredOn(human ->
                human.getName().contains("i"))
                .containsOnly(a,j,k);

    }

    @Test
    void filter_test2() {
        //given
        List<Human> list = new ArrayList<>();
        Human k = new Human("Kim",22);
        Human a = new Human("Aim",23);
        Human p = new Human("Park",42);
        Human j = new Human("Jin",12);
        Human y = new Human("yun",32);

        //when
        list.add(k);
        list.add(a);
        list.add(p);
        list.add(j);
        list.add(y);

        Assertions.assertThat(list).
                filteredOn("age", 22)
                .containsOnly(k);
    }


    @Test
    void filter_test3() {
        //given
        List<Human> list = new ArrayList<>();
        Human k = new Human("Kim",22);
        Human a = new Human("Aim",23);
        Human p = new Human("Park",42);
        Human j = new Human("Jin",12);
        Human y = new Human("yun",32);

        //when
        list.add(k);
        list.add(a);
        list.add(p);
        list.add(j);
        list.add(y);

        Assertions.assertThat(list)
                .filteredOn("age", notIn(22))
                .containsOnly(k);
    }


    @Test
    void filter_test4() {
        //given
        List<Human> list = new ArrayList<>();
        Human k = new Human("Kim",22);
        Human a = new Human("Aim",23);
        Human p = new Human("Park",42);
        Human j = new Human("Jin",12);
        Human y = new Human("yun",32);

        //when
        list.add(k);
        list.add(a);
        list.add(p);
        list.add(j);
        list.add(y);

        Assertions.assertThat(list).extracting("name")
                .contains("Kim","Aim","Park","Jin","yun");
    }

    @Test
    void filter_test6() {
        //given
        List<Human> list = new ArrayList<>();
        Human k = new Human("Kim",22);
        Human a = new Human("Aim",23);
        Human p = new Human("Park",42);
        Human j = new Human("Jin",12);
        Human y = new Human("yun",32);

        //when
        list.add(k);
        list.add(a);
        list.add(p);
        list.add(j);
        list.add(y);

        Assertions.assertThat(list).extracting("name","age")
                .contains(tuple("Kim",22)
                        ,tuple("Aim",23)
                        ,tuple("Park",42)
                        ,tuple("Jin",12)
                        ,tuple("yun",32));
    }

    @Test
    void stringAssertions()throws Exception{
        String s = "ABCDE";

        Assertions.assertThat(s).startsWith("A").contains("BCD").endsWith("E");

    }


    @Test
    void DBB_st()throws Exception{
        //given

        //when
        Throwable t = catchThrowable(()-> {
            throw new Exception("Exception");
        });
        //then
        Assertions.assertThat(t).isInstanceOf(Exception.class)
                .hasMessageContaining("Exception");

    }
}