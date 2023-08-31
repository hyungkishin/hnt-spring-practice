package com.example.hntspringpractice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//@SpringBootApplication
public class HntSpringPracticeApplication {

    public static void main(String[] args) {
//        SpringApplication.run(HntSpringPracticeApplication.class, args);

        // ApplicationContext 를 생성하고 초기화 한다.
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 컨텍스트에서 Bean 을 가져온다.
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");

        // sayHello() 메서드를 호출하여 "Hello World"를 출력한다.
        helloWorldService.sayHello();

        // 사용이 끝난 컨텍스트를 닫는다.
        context.close();
    }

}
