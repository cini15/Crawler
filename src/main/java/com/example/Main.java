package com.example;

import com.example.service.WebCrawler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebCrawler crawler= new WebCrawler();
        crawler.seedStatistic();
    }
}
