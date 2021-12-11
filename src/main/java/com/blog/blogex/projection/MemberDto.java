package com.blog.blogex.projection;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class MemberDto {

    @Setter
    @NoArgsConstructor
    public static class MemberDtoByBean{
        private String name;
        private int age;
    }

    @NoArgsConstructor
    public static class MemberDtoByField{
        private String name;
        private int age;
    }
    public static class MemberDtoByConstructor{
        private String name;
        private int age;

        public MemberDtoByConstructor(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Getter
    public static class MemberDtoByQueryProjection{
        private String name;
        private int age;

        @QueryProjection
        public MemberDtoByQueryProjection(String name, int age) {
            this.name = name;
            this.age = age;
        }

    }
}
