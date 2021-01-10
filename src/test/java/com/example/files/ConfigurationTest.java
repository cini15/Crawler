package com.example.files;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationTest {

    @Test
    void init() {
        Configuration configuration=new Configuration();
        configuration.init();
        assertFalse(configuration.getSeed().isEmpty());
        assertFalse(configuration.getTerms().isEmpty());
    }

}