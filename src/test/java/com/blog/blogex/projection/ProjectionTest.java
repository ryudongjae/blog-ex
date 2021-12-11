package com.blog.blogex.projection;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.blog.blogex.projection.QMember.member;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectionTest {
    @Mock
    JPAQueryFactory jpaQueryFactory;

    @Mock
    MemberRepository memberRepository;
    @Test
    void ProjectionByTuple()throws Exception{
        List<Tuple>tuples = jpaQueryFactory
                .select(member.name, member.age)
                .from(member)
                .fetch();

        Assertions.assertThat(tuples).hasSize(0);
    }

    @Test
    void ProjectionByBean()throws Exception{
        List<MemberDto.MemberDtoByBean> memberDto = jpaQueryFactory
                .select(Projections.bean(MemberDto.MemberDtoByBean.class,member.name,member.age))
                .from(member)
                .fetch();

        memberDto.forEach(System.out::println);
    }

    @Test
    void ProjectionByField()throws Exception{
        List<MemberDto.MemberDtoByField> memberDto = jpaQueryFactory
                .select(Projections.fields(MemberDto.MemberDtoByField.class, member.name, member.age))
                .from(member)
                .fetch();
    }


    @Test
    void ProjectionByConstructor()throws Exception{
        List<MemberDto.MemberDtoByConstructor> memberDto = jpaQueryFactory
                .select(Projections.constructor(MemberDto.MemberDtoByConstructor.class, member.name, member.age))
                .from(member)
                .fetch();
    }


    @Test
    void ProjectionByQueryProjection()throws Exception{
        List<MemberDto.MemberDtoByQueryProjection> memberDto = jpaQueryFactory
                .select(new QMemberDto_MemberDtoByQueryProjection( member.name, member.age))
                .from(member)
                .fetch();
    }

}