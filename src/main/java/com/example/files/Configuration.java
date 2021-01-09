package com.example.files;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Configuration {
    private  List<String> seeds;
    private List<String> terms;
    private final String configSeeds= "/config.csv";

    public Configuration() {
        this.seeds = new ArrayList<>();
        this.terms = new ArrayList<>();
    }

    public void init() {
        loadFromFileConfig(configSeeds);

    }

    private void loadFromFileConfig(String configSeeds) {
        InputStream input=Configuration.class.getResourceAsStream(configSeeds);
        List<String> list = new BufferedReader(new InputStreamReader(input)).lines().collect(Collectors.toList());
        terms= list.stream()
                .limit(1)
                .flatMap(s ->
                        Arrays.stream(s.split(","))
                                .map(p->p.replaceAll("\"",""))
                        ).collect(Collectors.toList());
        seeds= list.stream()
                .skip(1)
                .map(s ->
                        s.replaceAll("\"",""))
                .collect(Collectors.toList());
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
