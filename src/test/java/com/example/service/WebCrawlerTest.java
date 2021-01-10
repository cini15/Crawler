package com.example.service;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerTest {
    @Test
    void createCrawer(){
        String[] args= null;
        WebCrawler crawler= new WebCrawler(args);
        assertEquals(8,crawler.getCurrentDepth());
        args= new String []{"\"https://en.wikipedia.org/wiki/Elon_Musk\"\",\"Mask\",\"no Mask\"","10"};
        crawler=new WebCrawler(args);
        assertEquals(10,crawler.getCurrentDepth());

    }



    @Test
    void createCrawer1() {
        String[] args= new String []{"\"https://en.wikipedia.org/wiki/Elon_Musk\",\"Mask\",\"no Mask\""};
        WebCrawler crawler=new WebCrawler(args);
        assertEquals(8,crawler.getCurrentDepth());
    }



    @Test
    void getFiveBestSeeds() {
        WebCrawler web = new WebCrawler();
        web.seedStatistic();

        Map<String, List<Integer>> statistic = web.getStatistic();
        Map<String, List<Integer>> fiveBestSeeds = web.getTenBestSeeds();


    }

    @Test
    void setCurrentDepth() {

    }
}