package com.example.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Configuration {
    private String seed;
    private List<String> terms;
    private final String configSeeds = "/config.csv";
    private Path pathConfig;

    public Configuration() {
    }

    public void init() {
        File file = new File(this.getClass().getResource(configSeeds).getPath());
        pathConfig = file.toPath();
        String strings = loadFromFileConfig();
        String[] arr=strings.split(",");
        seed=arr[0].replaceAll("\"","");
        terms= Arrays.stream(arr)
                .skip(1)
                .map(s -> s.replaceAll("\"",""))
                .collect(Collectors.toList());

    }

    private String loadFromFileConfig() {
//        InputStream input=Configuration.class.getResourceAsStream(configSeeds);
//        return new BufferedReader(new InputStreamReader(input))
//                .lines().collect(Collectors.toList());

        try {
            return Files.readAllLines(pathConfig).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToFileConfig() {
//        try {
//            String res=seeds.stream().collect(Collectors.joining("\",\"","\"","\""));
//            Files.write(pathConfig, Collections.singleton(res));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    public String getSeed() {
        return seed;
    }

    public List<String> getTerms() {
        return terms;
    }
}
