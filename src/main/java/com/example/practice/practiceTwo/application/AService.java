package com.example.practice.practiceTwo.application;

import com.example.practice.practiceTwo.common.HntAutowire;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
            this.bService = ac.getBean(BService.class);
        }

        // ApplicationContext를 spring 이 주입해줍니다.
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) {
            this.ac = applicationContext;
        }
    }
}
