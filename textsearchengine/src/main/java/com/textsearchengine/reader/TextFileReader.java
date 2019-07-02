package com.textsearchengine.reader;

import com.textsearchengine.model.FileWordCounter;
import com.textsearchengine.util.TestSearchUtil;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextFileReader implements Reader {
    @Override
    public Map<String, FileWordCounter> readFile(Path file) {
        Map<String, FileWordCounter> indexedWords = null;
        try {
            indexedWords = getStringFileWordCounterMap(file);
       } catch (IOException e) {
            e.printStackTrace();
        }
        return indexedWords;

    }

   private Map<String, FileWordCounter> getStringFileWordCounterMap(Path file) throws IOException {
        Map<String, FileWordCounter> indexedWords = new HashMap<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file.toFile())))) {
            String fileName = file.getFileName().toString();
            int rowCount=0;
            while (scanner.hasNextLine()) {
                   String line=scanner.nextLine();
                   if(line !=null && !line.isEmpty()) {
                        int columnCount=0;
                            while (scanner.hasNext()) {
                             String word = scanner.next();
                               String normalizedWord = TestSearchUtil.normalizeWord(word);
                                FileWordCounter count = indexedWords.containsKey(normalizedWord) ? indexedWords.get(normalizedWord).incrementWordCount() : new FileWordCounter(fileName, normalizedWord, 1);
                                count.setColoumIndex(columnCount);
                                count.setRowIndex(rowCount);
                               indexedWords.put(normalizedWord, count);
                           }
                           rowCount++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return indexedWords;
    }



}
