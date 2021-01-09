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

    public WebCrawler() {
        parser = new HtmlParser();
        conf = new Configuration();
    }

    public void seedStatistic() throws IOException, InterruptedException {
        conf.init();
        statistic = new HashMap<>();
        parser.setTerms(conf.getTerms());
        List<Integer> numbers;
        for (String seed : conf.getSeeds()) {
            numbers = parser.parse(loadHtml(seed));
            statistic.put(seed, numbers);
        }
        ResultStatistic result= new ResultStatistic();
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

    public List<String> getTerms() {
        return conf.getTerms();
    }

    private String loadHtml(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return  response.body();
    }
}
