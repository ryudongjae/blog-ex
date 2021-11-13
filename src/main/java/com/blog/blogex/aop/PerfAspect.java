package com.blog.blogex.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //이 어노테이션을 붙여 이 클래스가 Aspect를 나타내는 클래스라는 것을 명시
public class PerfAspect {

    //Around는 타겟 메서드를 감싸서 특정 Advice 를 실행한다는 의미이다.
    @Around("@annotation(ExecutionTimeChecker)")
    public Object logPerf(ProceedingJoinPoint pjp)throws Throwable{
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println(System.currentTimeMillis()-begin);
        return retVal;
    }

}
