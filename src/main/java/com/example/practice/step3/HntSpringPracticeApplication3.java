package com.example.practice.step3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HntSpringPracticeApplication3 {

    public static void main(String[] args) {
        // ApplicationContext 초기화
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // @Component 어노테이션이 붙은 클래스를 패키지 내에서 스캔
        context.scan("com.example.practice.step3");

        // ApplicationContext를 새로 고침하여 빈 생성을 완료한다.
        context.refresh();

        // HelloWorldService 빈을 검색하여 사용한다.
        HelloWorldService helloWorldService = context.getBean(HelloWorldService.class);
        helloWorldService.sayHello();
    }

}