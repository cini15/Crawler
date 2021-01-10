package com.example.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Configuration {
    private String seed;
    private List<String> terms;
    private final String configSeeds = "\\config.csv";
    private Path pathConfig;

    public Configuration() {
    }

    public void init(String args) {
        if (args == null)
            init();
        else {
            String[] arr = args.split(",");
            seed = arr[0].replaceAll("\"", "");
            terms = Arrays.stream(arr)
                    .skip(1)
                    .map(s -> s.replaceAll("\"", ""))
                    .collect(Collectors.toList());
            if (seed.length() == 0 | terms.isEmpty())
                throw new IllegalArgumentException("data entry error");
        }
    }

    public void init() {
        File file = new File(this.getClass().getResource(configSeeds).getPath());
        pathConfig = file.toPath();
        String strings = loadFromFileConfig();
        init(strings);
    }

    private String loadFromFileConfig() {
        try {
            return Files.readAllLines(pathConfig).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getSeed() {
        return seed;
    }

    public List<String> getTerms() {
        return terms;
    }
}
