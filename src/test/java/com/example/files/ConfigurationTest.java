package com.example.files;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void init() {
        Configuration configuration=new Configuration();
        configuration.init();
        System.out.println(configuration.getSeed()+ " : "+configuration.getTerms().toString());
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