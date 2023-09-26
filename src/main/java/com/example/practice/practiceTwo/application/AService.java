package com.example.practice.practiceTwo.application;

import com.example.practice.practiceTwo.common.HntAutowire;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface AService {

    String hello();

    class AServiceImpl implements AService, InitializingBean, ApplicationContextAware {

        @HntAutowire
        private BService bService;

        private ApplicationContext ac;


        @Override
        public String hello() {
            return "hello" + " " + bService.hello();
        }

        @Override
        public void afterPropertiesSet() {
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

        // ApplicationContext를 spring 이 주입해줍니다.
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) {
            this.ac = applicationContext;
        }
    }
}
