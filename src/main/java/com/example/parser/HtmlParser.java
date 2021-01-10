package com.example.parser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HtmlParser {
    private List<String> terms;
    Map<String, List<Integer>> statistic = new HashMap<>();
    HttpClient client = HttpClient.newHttpClient();

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public Map<String, List<Integer>> parse(String seed, int currentDepth) {
        List<String> strings = new ArrayList<>();
        strings.add(seed);
        strings.addAll(listOfHrefs(seed));
        List<CompletableFuture> futures = new ArrayList<>();
        int count = 0;
        strings = strings.stream().distinct().collect(Collectors.toList());
        for (String href : strings) {
            if (count >= currentDepth) {
                CompletableFuture
                        .allOf(futures.toArray(new CompletableFuture[0]))
                        .join();
                futures.clear();
                count = statistic.size();
            }
            if (statistic.size() >= currentDepth)
                return statistic;
            if (!statistic.containsKey(href)) {
                futures.add(getFuture(href));
                count++;
            }
        }
        strings.forEach(s -> parse(s, currentDepth));
        return statistic;
    }

    private CompletableFuture<List<Integer>> getFuture(String href) {
        CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        return countOfTerm(href);
                    } catch (InterruptedException | IOException e) {
//                e.printStackTrace();
                        return new ArrayList<>();
                    }
                }
        );
        future.thenAccept(integers ->
                {
                    if (!integers.isEmpty())
                        statistic.put(href, integers);
                }
        );
        return future;
    }

    public List<String> listOfHrefs(String seed) {
        List<String> hrefs = new ArrayList<>();
        String responseBody;
        try {
            responseBody = loadHtml(seed);
            Matcher matcher = Pattern.compile("\"(https?://.*?)\"").matcher(responseBody);
            while (matcher.find()) {
                hrefs.add(matcher.group().replaceAll("\"", ""));
            }
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            return hrefs;
        }
        return hrefs;
    }

    public List<Integer> countOfTerm(String seed) throws IOException, InterruptedException {
        String responseBody = loadHtml(seed);
        List<Integer> countTerms = new ArrayList<>();
        for (String term : terms) {
            Integer count = 0;
            Matcher matcher = Pattern.compile(term).matcher(responseBody);
            while (matcher.find()) {
                count++;
            }
            countTerms.add(count);
        }
        countTerms.add(countTerms.stream()
                .reduce(0, Integer::sum));
        return countTerms;
    }

    private String loadHtml(String url) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
