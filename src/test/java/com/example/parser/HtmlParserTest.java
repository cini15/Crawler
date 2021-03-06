package com.example.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class HtmlParserTest {

    @Test
    void countTerms() throws IOException, InterruptedException {
        HtmlParser parser=new HtmlParser();
        parser.setTerms(List.of("Tesla","Musk","Elon Musk"));
        String exp="https://en.wikipedia.org/wiki/Elon_Musk";
        assertEquals(4, parser.countOfTerm(exp).size());
    }

    @Test
    void parse()  {
        String exp="https://en.wikipedia.org/wiki/Elon_Musk";

        HtmlParser parser=new HtmlParser();
        parser.setTerms(List.of("Tesla","Musk","Elon Musk"));

        Map<String, List<Integer>> map = parser.parse(exp, 20);
        assertFalse(map.isEmpty());
        assertEquals(4, map.get(exp).size());
        map.forEach((s, integers) -> {
            System.out.println(s + " : "+
            integers.stream().map(Object::toString)
                    .collect(Collectors.joining(",")));
        });
    }

    @Test
    void countOfTerm() {
        HtmlParser parser = new HtmlParser();

        List<String> list = parser.listOfHrefs("https://en.wikipedia.org/wiki/Elon_Musk");
        assertFalse(list.isEmpty());
    }
    @Test
    void parseImg(){
        HtmlParser parser= new HtmlParser();
        List<String> list = parser.listOfHrefs("https://en.wikipedia.org/wiki/Elon_Musk");
        for (String str:list) {
            assertFalse(str.contains(".img"));
            assertFalse(str.contains(".png"));
            assertFalse(str.contains(".ico"));
        }
    }
}