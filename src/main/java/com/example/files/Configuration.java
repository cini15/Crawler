package com.example.files;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private List<String> seeds;
    private List<String> terms;
    private Integer seedsCount;

    public Configuration() {
        this.seeds = new ArrayList<>();
        this.terms = new ArrayList<>();
    }

    public void init() {
        seeds.add("https://en.wikipedia.org/wiki/Elon_Musk");
        terms.add("Tesla");
        terms.add("Musk");

        seedsCount=seeds.size();
    }

    public List<String> getSeeds() {
        return seeds;
    }

    public List<String> getTerms() {
        return terms;
    }

    public Integer getSeedsCount() {
        return seedsCount;
    }
}
