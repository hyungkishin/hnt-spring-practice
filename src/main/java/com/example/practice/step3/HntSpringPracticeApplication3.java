package com.example.practice.step3;

import com.example.practice.step3.config.CustomMyApplicationContext;
import com.example.practice.step3.service.HelloWorldService;

public class HntSpringPracticeApplication3 {

    public static void main(String[] args) {
        // CustomMyApplicationContext 초기화
        CustomMyApplicationContext context = new CustomMyApplicationContext("com.example.practice.step3.service");

        HelloWorldService helloWorldService = context.getBean(HelloWorldService.class);
        helloWorldService.sayHello();
    }

}