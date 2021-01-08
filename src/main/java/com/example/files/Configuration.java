package com.example.files;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private List<String> seeds;
    private List<String> terms;

    public Configuration() {
        this.seeds = new ArrayList<>();
        this.terms = new ArrayList<>();
    }

    public void init() {
        seeds.add("https://en.wikipedia.org/wiki/Elon_Musk");
        seeds.add("https://en.wikipedia.org/wiki/SpaceX");
        seeds.add("https://en.wikipedia.org/wiki/Tesla,_Inc.");
        seeds.add("https://en.wikipedia.org/wiki/The_Boring_Company");
        seeds.add("https://en.wikipedia.org/wiki/Neuralink");
        terms.add("Tesla");
        terms.add("Musk");
        terms.add("Elon Musk");


    }

    public List<String> getSeeds() {
        return seeds;
    }

    public List<String> getTerms() {
        return terms;
    }

    public Integer getSeedsCount() {
        return seeds.size();
    }
}
