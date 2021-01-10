package com.example.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
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
        parser.setTerms(List.of("Tesla","Musk","Elon Musk"));

        Map<String, List<Integer>> list = parser.parse(exp, 20);

    }

    @Test
    void countOfTerm() throws IOException, InterruptedException {
        HtmlParser parser = new HtmlParser();

        List<String> list = parser.listOfHrefs("https://en.wikipedia.org/wiki/Elon_Musk");

    }
}