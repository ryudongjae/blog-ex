package com.blog.blogex.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS) // 어느 시점까지 어노테이션을 가지고 갈 것인지
@Target(ElementType.METHOD) //어노테이션이 적용될 레벨
public @interface ExecutionTimeChecker {
}
