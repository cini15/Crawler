package com.example.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultStatistic {
    private String fileName="./result.csv";

    public ResultStatistic() {
    }

    public ResultStatistic(String fileName) {
        this.fileName = fileName;
    }

    public void writeStatistic(Map<String, List<Integer>> map){
        List<String> statList = mapToLines(map);
        writeToFile(fileName,statList);
    }

    private void writeToFile(String fileName,  List<String> stat) {
        Path filePath= Path.of(fileName);
        try {
            if (Files.notExists(filePath, LinkOption.NOFOLLOW_LINKS))
            Files.createFile(filePath);
            Files.write(filePath,stat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> mapToLines(Map<String, List<Integer>> map) {
        return map.entrySet().stream()
                .map((p) -> "\"" + p.getKey() + "\","
                        + p.getValue().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining("," )))
                .collect(Collectors.toList());
    }

    public String getFileName() {
        return fileName;
    }

}
