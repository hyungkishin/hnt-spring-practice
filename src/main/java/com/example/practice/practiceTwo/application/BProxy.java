package com.example.practice.practiceTwo.application;

import com.example.practice.practiceTwo.application.service.BService;
import org.springframework.util.StopWatch;

public class BProxy implements BService {

    private BService target;

    public BProxy(BService target) {
        this.target = target;
    }

    @Override
    public String hello() {
        pre();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String result = target.hello();

        stopWatch.stop();
        long duration = stopWatch.getTotalTimeMillis();

        post();
        System.out.println("duration Time = " + duration);

        return result;
    }

    public void pre() {
        System.out.println("B Proxy hello call - before");
    }

    private void post() {
        System.out.println("B Proxy hello call - after");
    }
}
