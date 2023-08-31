package com.example.practice.step1;

import org.springframework.context.support.GenericApplicationContext;

public class HntSpringPracticeApplication1 {

    public static void main(String[] args) {
        // ApplicationContext 생성 및 초기화.
        GenericApplicationContext context = new GenericApplicationContext();

        // helloWorldService 라는 이름으로 HelloWorldService 클래스를 빈으로 등록한다.
        context.registerBean("helloWorldService", HelloWorldService.class, HelloWorldService::new);

        // 빈 등록을 완료하기 위해 ApplicationContext 를 갱신한다.
        //  Spring 컨텍스트는 빈의 등록과 초기화를 완료하고, 애플리케이션에서 이를 올바르게 사용할 준비를 마치게 된다고 한다.
        context.refresh();

        // 컨텍스트에서 등록했던 빈을 가져온다.
        HelloWorldService helloWorldService = context.getBean("helloWorldService", HelloWorldService.class);

        // sayHello() 메서드를 호출하여 "Hello World"를 출력한다.
        helloWorldService.sayHello();
    }

}
