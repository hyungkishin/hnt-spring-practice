package com.example.practice.practiceOne.step3.config;

public interface SimpleBeanFactory {
    <T> T getBean(Class<T> requiredType);
}
