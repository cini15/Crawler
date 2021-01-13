package com.example.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HtmlParser {
    private List<String> terms;
    Map<String, List<Integer>> statistic = new HashMap<>();
    HttpClient client = HttpClient.newHttpClient();
    CompletableFuture<List<Integer>> future;

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    public Map<String, List<Integer>> parse(String seed, int currentDepth) {
        List<String> strings = new ArrayList<>();
        strings.add(seed);
        strings.addAll(listOfHrefs(seed));
//        List<CompletableFuture> futures = new ArrayList<>();
        int count = 0;
        for (String href : strings) {
            if (count >= currentDepth) {
//                CompletableFuture
//                        .allOf(futures.toArray(new CompletableFuture[0]))
//                        .join();
//                futures.clear();
                future.join();
                count = statistic.size();
            }
            if (statistic.size() >= currentDepth)
                return statistic;
            if (!statistic.containsKey(href)) {
//                futures.add(
                getFuture(href)
//                )
                ;
                count++;
            }
        }
        strings.forEach(s -> parse(s, currentDepth-strings.size()));
        return statistic;
    }

    private CompletableFuture<List<Integer>> getFuture(String href) {
//        CompletableFuture<List<Integer>>
        future = CompletableFuture.supplyAsync(() -> {
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
        Set<String> hrefs = new HashSet<>();
        String responseBody;
        try {
            responseBody = loadHtml(seed);
            Matcher matcher = Pattern.compile("\"(https?://.*?(?!.img))\"").matcher(responseBody);
            while (matcher.find()) {
                hrefs.add(matcher.group(1));
                System.out.println(matcher.group(1));
            }
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            return new ArrayList<>();
        }
        return new ArrayList<>(hrefs);
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

        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(2)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
//        URL oracle = new URL(url);
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(oracle.openStream()));
//
//        String inputLine = "";
//        StringBuilder builder = new StringBuilder();
//        while ((inputLine = in.readLine()) != null)
////            System.out.println(inputLine); //Можно//  накапливать в StringBuilder а потом присвоить перемной String результат накопления
//            builder.append(inputLine);
//            in.close();
//        return builder.toString();
    }

}
