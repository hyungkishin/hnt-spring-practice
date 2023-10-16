package com.example.practice.practiceTwo;

import com.example.practice.practiceTwo.application.AServiceImpl;
import com.example.practice.practiceTwo.application.BProxy;
import com.example.practice.practiceTwo.application.service.AService;
import com.example.practice.practiceTwo.application.service.BService;
import org.springframework.context.support.GenericApplicationContext;

public class Application {

    public static void main(String[] args) {
        GenericApplicationContext ac = new GenericApplicationContext();
        ac.registerBean(BService.class, () -> new BProxy(() -> "world"));
        ac.registerBean(AService.class, AServiceImpl::new);
        ac.refresh();

        AService aService = ac.getBean(AService.class);
        System.out.println(aService.hello());
    }
}
