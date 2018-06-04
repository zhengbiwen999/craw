package com.zbw.reactorModel;


import org.junit.Test;

import java.io.FileInputStream;
import java.util.HashMap;

public class WordCount {

    private static final HashMap<String,Integer> counts = new HashMap<>();

    public static void main(String[] args) {
        Iterable<Page> pages = new Pages(10000,"enwiki.xml");
        for(Page page : pages){
            Iterable<String> words = new Words(page.getText());
            for(String word : words){
                countWord(word);
            }
        }
        System.out.println(counts);
    }
    private static void countWord(String word){
        Integer currentCount = counts.get(word);
        if(currentCount == null) counts.put(word,1);
        else
            counts.put(word,currentCount+1);
    }

    @Test
    public void test() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("/Users/bwz/Downloads/enwiki.yml");
    }

}
