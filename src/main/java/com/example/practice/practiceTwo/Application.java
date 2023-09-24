package com.example.practice.practiceTwo;

import com.example.practice.practiceTwo.application.AService;
import com.example.practice.practiceTwo.application.BService;
import org.springframework.context.support.GenericApplicationContext;

public class Application {

    public static void main(String[] args) {
        GenericApplicationContext ac = new GenericApplicationContext();

        ac.registerBean(AService.AServiceImpl.class);
        ac.registerBean(BService.BServiceImpl.class);
        ac.refresh();

        AService aService = ac.getBean(AService.class);
        System.out.println(aService.hello());

    }
}
