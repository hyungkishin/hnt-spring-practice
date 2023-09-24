package com.example.practice.practiceTwo.application;

public interface BService {

    String hello();

    public static class BServiceImpl implements BService {

        @Override
        public String hello() {
            return "world";
        }
    }
}
