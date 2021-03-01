package com.javabom.toby.chapter6.term;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AopUserServiceTest {

    @DisplayName("표현식으로 포인트컷을 지정할 수 있다")
    @Test
    void methodSignature() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello(..))");

        /**
         * 포인트컷은 클래스필터와 매서드매처를 둘다 지정할 수 있다.
         */
        assertThat(pointcut.getClassFilter().matches(AopUserService.class)).isTrue();
        assertThat(pointcut.getMethodMatcher().matches(AopUserService.class.getMethod("bye"), AopUserService.class)).isFalse();
    }

    @DisplayName("포인트컷으로 인터페이스를 지정하면 해당 인터페이스를 구현한 메서드만 적용된다")
    @Test
    void interfacePointcut() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* *..AopTestInterface.*(..))");

        assertThat(pointcut.getClassFilter().matches(AopUserService.class)).isTrue();
        assertThat(pointcut.getMethodMatcher().matches(AopUserService.class.getMethod("test"), AopUserService.class)).isTrue();
        assertThat(pointcut.getMethodMatcher().matches(AopUserService.class.getMethod("hello"), AopUserService.class)).isFalse();
    }

    @DisplayName("포인트컷 표현식의 클래스 이름에 적용되는 패턴은 타입패턴이다")
    @Test
    void typePattern() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* *..AnotherAopUserService.*(..))");

        assertThat(pointcut.getClassFilter().matches(AopUserService.class)).isTrue();
    }

    @Test
    void advice() {
        AbstractApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:applicationContext-test.xml");

        AopTestInterface aopUserService = ctx.getBean("aopUserService", AopTestInterface.class);
        aopUserService.test();
    }
}