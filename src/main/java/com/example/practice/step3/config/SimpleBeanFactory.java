package com.example.practice.step3.config;

public interface SimpleBeanFactory {
    <T> T getBean(Class<T> requiredType);
}
