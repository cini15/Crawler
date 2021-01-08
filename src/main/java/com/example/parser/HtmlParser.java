package com.example.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    private List<String> terms;
    public void setTerms(List<String> terms) {
        this.terms=terms;

    }

    public List<Integer> parse(String seed) {
        List<Integer> statistic= new ArrayList<>();
        for (String term:terms) {
            statistic.add(countOfTerm(seed,term));
        }
        int sum = statistic.stream()
                .reduce(0, Integer::sum);
        statistic.add(sum);
        return statistic;
    }

    private Integer countOfTerm(String seed, String term) {
        Integer count =0;
        Matcher matcher = Pattern.compile(term).matcher(seed);
        while (matcher.find()){
            count++;
        }
        return count;
    }

}
