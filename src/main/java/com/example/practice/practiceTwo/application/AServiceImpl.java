package com.example.practice.practiceTwo.application;

import com.example.practice.practiceTwo.application.service.AService;
import com.example.practice.practiceTwo.application.service.BService;
import com.example.practice.practiceTwo.common.HntAutowire;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AServiceImpl implements AService, InitializingBean, ApplicationContextAware {

    @HntAutowire
    private BService bService;

    private ApplicationContext ac;

    @Override
    public String hello() {
        return "hello" + " " + bService.hello();
    }

    @Override
    public void afterPropertiesSet() {
        bService = new BProxy(bService);

        // 기존의 필드 주입 로직은 유지됨
        Field[] fields = this.getClass().getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(HntAutowire.class))
                .forEach(field -> {
                    Object bean = ac.getBean(field.getType());
                    try {
                        field.setAccessible(true);
                        field.set(this, bean);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("@HntAutowire 빈 주입 실패", e);
                    }
                });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ac = applicationContext;
    }

}