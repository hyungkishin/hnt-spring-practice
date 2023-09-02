package com.example.practice.step3.config;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CustomMyApplicationContext implements SimpleBeanFactory {

    private final Map<Class<?>, Object> beanMap = new HashMap<>();

    public CustomMyApplicationContext(String basePackage) {
        scanPackage(basePackage);
    }

    private void scanPackage(String packageName) {
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());

                for (File file : directory.listFiles()) {
                    if (file.isDirectory()) {
                        // 재귀적으로 하위 패키지를 스캔합니다.
                        scanPackage(packageName + "." + file.getName());
                    } else if (file.getName().endsWith(".class")) {
                        // .class 파일을 처리합니다.
                        String className = file.getName().substring(0, file.getName().length() - 6);
                        Class<?> clazz = Class.forName(packageName + "." + className);

                        // @Component 어노테이션이 있는지 확인합니다.
                        if (clazz.isAnnotationPresent(Component.class)) {
                            Object instance = clazz.getDeclaredConstructor().newInstance();
                            beanMap.put(clazz, instance);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return requiredType.cast(beanMap.get(requiredType));
    }
}
