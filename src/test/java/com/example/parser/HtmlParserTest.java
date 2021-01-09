package com.example.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

class HtmlParserTest {

    @Test
    void setTerms() {
    }

    @Test
    void parse() throws IOException, InterruptedException {
        String exp="https://en.wikipedia.org/wiki/Elon_Musk";

        HtmlParser parser=new HtmlParser();
        parser.setTerms(List.of("Tesla","Musk"));
        Map<String, List<Integer>> list = parser.parse(exp, 20);
        for (String keq:list.keySet()
             ) {
            System.out.println(keq+ " : "+ list.get(keq));
        }
    }

    @Test
    void countOfTerm() throws IOException, InterruptedException {
        HtmlParser parser = new HtmlParser();

        Set<String> list = parser.listOfHrefs("https://en.wikipedia.org/wiki/Elon_Musk");

    }
}