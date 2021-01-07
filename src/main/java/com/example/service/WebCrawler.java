package com.example.service;

import com.example.files.Configuration;
import com.example.parser.HtmlParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebCrawler {
    private HtmlParser parser =null;
    private Configuration conf=null;

    public WebCrawler() {
        parser= new HtmlParser();
        conf=new Configuration();
    }

    public void seedStatistic() throws IOException, InterruptedException {
        conf.init();
        Map<String, List<Integer>> statistic = new HashMap<>();
        parser.setTerms(conf.getTerms());
        List<String> seeds = conf.getSeeds();
        List<Integer> numbers;
        for (String seed : seeds) {
            loadHtml(seed);
            numbers= parser.parse(seed);
            statistic.put(seed,numbers);
        }

    }
    private String loadHtml(String url) throws IOException, InterruptedException {
        String html=null;
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse<String> response= client.send(request, HttpResponse.BodyHandlers.ofString());
        html= response.body();

        return html;
    }
}
