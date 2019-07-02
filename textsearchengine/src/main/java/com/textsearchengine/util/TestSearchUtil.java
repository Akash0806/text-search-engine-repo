package com.textsearchengine.util;

import java.util.Map;

public class TestSearchUtil {



    public static String normalizeWord(String word) {
        return word.replaceAll("[^\\p{IsAlphabetic}]", "").toLowerCase();
    }

    public static void printResult(Map<String, Long> result) {
        if (result.isEmpty()) {
            System.out.println("no matches found");
        } else {
            result.entrySet().forEach(e -> {
                System.out.println(e.getKey() + " : " + e.getValue() + "%");
            });
        }
    }
}
