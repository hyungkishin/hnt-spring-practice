package com.example.practice.practiceOne.step2;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class HntSpringPracticeApplication2 {

    public static void main(String[] args) {
        // ApplicationContext 초기화
        GenericApplicationContext context = new GenericApplicationContext();

        // 컴포넌트 스캐너 등록
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(Object.class)); // 모든 클래스 포함

        // 패키지 스캔 및 빈 등록
        scanAndRegisterBeans("com.example.practice.step2", scanner, context);

        // ApplicationContext 새로고침
        context.refresh();

        // 빈 검색 및 사용
        HelloWorldService helloWorldService = context.getBean(HelloWorldService.class);
        helloWorldService.sayHello();
    }

    private static void scanAndRegisterBeans(String basePackage,
                                             ClassPathScanningCandidateComponentProvider scanner,
                                             GenericApplicationContext context) {
        // 지정된 패키지에서 빈 후보 찾기
        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            try {
                String beanClassName = bd.getBeanClassName();
                // 클래스 이름으로 클래스 로드
                Class<?> beanClass = Class.forName(beanClassName);
                // 빈 등록
                context.registerBean(beanClass);
            } catch (ClassNotFoundException e) {
                // 클래스를 찾을 수 없는 경우 에러 출력
                e.printStackTrace();
            }
        }
    }
}