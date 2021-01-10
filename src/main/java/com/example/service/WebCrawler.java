package com.example.service;

import com.example.files.Configuration;
import com.example.files.ResultStatistic;
import com.example.parser.HtmlParser;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

public class WebCrawler {
    private final HtmlParser parser;
    private final Configuration conf;
    private Map<String, List<Integer>> statistic;
    private final Integer defDepth = 8;
    private final Integer maxDepth = 10000;
    private int currentDepth;


    public WebCrawler() {
        parser = new HtmlParser();
        conf = new Configuration();
        currentDepth = defDepth;
    }

    public void seedStatistic() throws IOException, InterruptedException {
        conf.init();
        statistic = new HashMap<>();
        parser.setTerms(conf.getTerms());
        statistic.putAll(parser.parse(conf.getSeed(), currentDepth));
        ResultStatistic result = new ResultStatistic();
        result.writeStatistic(statistic);
    }

    public Map<String, List<Integer>> getStatistic() {
        return statistic;
    }

    public Map<String, List<Integer>> getFiveBestSeeds() {
        return statistic.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().get(e2.getValue().size() - 1),
                        e1.getValue().get(e1.getValue().size() - 1)))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }


    public void setCurrentDepth(int currentDepth) {
        if (currentDepth > maxDepth | currentDepth <= 1)
            throw new IllegalArgumentException("count of depth is dig (maxCount=" + maxDepth + ", min=1, def=" + defDepth);
        this.currentDepth = currentDepth;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }
}
