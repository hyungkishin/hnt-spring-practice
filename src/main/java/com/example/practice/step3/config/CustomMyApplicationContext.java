package com.example.practice.step3.config;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomMyApplicationContext {

    private final Map<Class<?>, Object> beanMap = new HashMap<>();

    public CustomMyApplicationContext(String packageName) {
        try {
            // 패키지 이름에서 경로를 생성합니다.
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File file = new File(resource.getFile());

                for (File classFile : Objects.requireNonNull(file.listFiles())) {
                    // .class 파일만 처리합니다.
                    if (classFile.getName().endsWith(".class")) {
                        String className = classFile.getName().substring(0, classFile.getName().length() - 6);
                        Class<?> clazz = Class.forName(packageName + '.' + className);

                        if (clazz.isAnnotationPresent(Component.class)) {
                            Object instance = clazz.getDeclaredConstructor().newInstance();
                            beanMap.put(clazz, instance);
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public <T> T getBean(Class<T> requiredType) {
        return requiredType.cast(beanMap.get(requiredType));
    }
}
