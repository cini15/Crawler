package com.example;

import com.example.service.WebCrawler;

public class Main {
    public static void main(String[] args) {
        if (args.length > 2) {
            throw new IllegalArgumentException("the number of arguments is more than 2 ");
        }
        WebCrawler crawler = new WebCrawler(args);
        crawler.seedStatistic();
        crawler.getTenBestSeeds()
                .forEach((s, integers) ->
                        System.out.println(s + " " + integers.toString()));
    }
}
