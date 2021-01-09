package com.example.parser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    private List<String> terms;
    Map<String,List<Integer>> statistic=new HashMap<>();
    public void setTerms(List<String> terms) {
        this.terms=terms;

    }

    public Map<String,List<Integer>> parse(String seed, int currentDepth) throws IOException, InterruptedException {
        if (!statistic.containsKey(seed))
            statistic.put(seed,countOfTerm(seed));

        Set<String> strings = listOfHrefs(seed);
        for (String href:strings) {
            if (currentDepth<=0)
                return statistic;
            try {
                statistic.put(href,countOfTerm(href));
                currentDepth--;
            } catch (Exception  e) {
//                e.printStackTrace();
            }
        }

        return statistic;
    }

    public Set<String> listOfHrefs(String seed) throws IOException, InterruptedException {
        Set<String> hrefs=new HashSet<>();
        String responseBody=loadHtml(seed);
        Matcher matcher=Pattern.compile("\"(https?://.*?)\"").matcher(responseBody);
        while (matcher.find()){
            hrefs.add(matcher.group().replaceAll("\"",""));
        }
        System.out.println("hrefs"+new Date());
        return hrefs;
    }

    List<Integer> countOfTerm(String seed) throws IOException, InterruptedException {
        String responseBody=loadHtml(seed);
        List<Integer> countTerms=new ArrayList<>();
        for (String term :terms){
            Integer count =0;
            Matcher matcher = Pattern.compile(term).matcher(responseBody);
            while (matcher.find()){
                count++;
            }
            countTerms.add(count);
        }
        countTerms.add(countTerms.stream()
                .reduce(0, Integer::sum));
        System.out.println("count"+new Date());
        return countTerms;
    }
    private String loadHtml(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("load"+new Date());
        return  response.body();
    }

}
