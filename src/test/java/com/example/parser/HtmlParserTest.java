package com.example.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class HtmlParserTest {

    @Test
    void setTerms() {
    }

    @Test
    void parse() {
        String exp= "Elon Reeve Musk FRS (/ˈiːlɒn/ EE-lon; born June 28, 1971) is a business magnate, industrial designer and engineer.[6] He is the founder, CEO, CTO and chief designer of SpaceX; early investor,[b] CEO and product architect of Tesla, Inc.; founder of The Boring Company; co-founder of Neuralink; and co-founder and initial co-chairman of OpenAI. He was elected a Fellow of the Royal Society (FRS) in 2018.[9][10] Also that year, he was ranked 25th on the Forbes list of The World's Most Powerful People,[11] and was ranked joint-first on the Forbes list of the Most Innovative Leaders of 2019.[12] As of January 6, 2020, Musk’s net worth was estimated by Forbes to US$167.2 billion,[1][13] making him the second-richest person in the world, behind Jeff Bezos.[14]";
        String pat="and";
//        Pattern pattern = Pattern.compile(pat);
//        Matcher matcher= pattern.matcher(exp);
//        while (matcher.find())
//        System.out.println(matcher.group());

        HtmlParser parser=new HtmlParser();
        parser.setTerms(List.of(pat,"Musk"));
        List<Integer> list = parser.parse(exp);
        System.out.println(list.toString());
    }
}