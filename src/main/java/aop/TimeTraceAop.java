package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop는 이 어노테이션이 필요함
@Component
public class TimeTraceAop {

    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // proceed
        long start = System.currentTimeMillis(); // 호출이 될 땜때마다 joinpoint로 원하는 것들을 할 수있음
        System.out.println("START = " + joinPoint.toString());
        try {
            // 다
            // 음 메소드로 진행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            String result = joinPoint.toString();
            System.out.println("End = " + result + "timeMs = " + timeMs + "ms");
        }
    }

}
