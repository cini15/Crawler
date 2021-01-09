package com.example.files;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void init() {
        Configuration configuration=new Configuration();
        configuration.init();
        configuration.getSeeds().forEach(System.out::println);
        configuration.getTerms().forEach(System.out::println);
    }

    @Test
    void getSeeds() {
    }

    @Test
    void getTerms() {
    }

    @Test
    void getSeedsCount() {
    }
}