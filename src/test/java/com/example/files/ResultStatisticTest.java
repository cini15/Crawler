package com.example.files;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResultStatisticTest {

    @Test
    void writeStatistic() throws IOException {

        ResultStatistic statistic= new ResultStatistic();
        Map<String, List<Integer>> map= new HashMap<>();
        map.put("test1", Arrays.asList(0,4,7,1,2,6));
        map.put("test2", Arrays.asList(0,4,7));
        statistic.writeStatistic(map);
        assertTrue(Files.exists(Path.of(statistic.getFileName())));
        assertTrue(Files.size(Path.of(statistic.getFileName()))>0);
        Files.delete(Path.of(statistic.getFileName()));
    }
}