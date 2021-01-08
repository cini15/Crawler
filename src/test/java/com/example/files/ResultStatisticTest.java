package com.example.files;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ResultStatisticTest {

    @Test
    @Disabled
    void writeStatistic() {

        ResultStatistic statistic= new ResultStatistic();
        Map<String, List<Integer>> map= new HashMap<>();
        map.put("test1", Arrays.asList(0,4,7,1,2,6));
        map.put("test2", Arrays.asList(0,4,7));
        statistic.writeStatistic(map);
    }
}