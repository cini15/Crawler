package com.example.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Configuration {
    private String seed;
    private List<String> terms;

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
        String strings = loadDefConfig();
        init(strings);
    }

    private String loadDefConfig() {
        return "\"https://en.wikipedia.org/wiki/Elon_Musk\",\"Tesla\",\"Musk\",\"Elon Musk\"";
    }

    public String getSeed() {
        return seed;
    }

    public List<String> getTerms() {
        return terms;
    }
}
