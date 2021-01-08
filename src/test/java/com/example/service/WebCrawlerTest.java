package com.example.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerTest {

    @Test
    void seedStatistic() {
    }

    @Test
    void getStatistic() {
    }

    @Test
    void getFiveBestSeeds() {
        WebCrawler web = new WebCrawler();
        try {
            web.seedStatistic();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String, List<Integer>> statistic = web.getStatistic();
        Map<String, List<Integer>> fiveBestSeeds = web.getFiveBestSeeds();

        System.out.println(web.getTerms());
        System.out.println(statistic.toString());
        System.out.println(fiveBestSeeds);

    }
}