package com.textsearchengine.cache;

import com.textsearchengine.model.FileWordCounter;
import com.textsearchengine.reader.FactoryReader;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAndWordCacheIndex {
   private  FactoryReader factoryReader;

     public FileAndWordCacheIndex(FactoryReader factoryReader){
         this.factoryReader = factoryReader;
     }
    public Map<String, List<FileWordCounter>> index(Stream<Path> stream, String type) {
        Stream<Map<String, FileWordCounter>> fileWords = stream
                .map(file -> {
                        return factoryReader.getReaderImplementation(type).readFile(file);
                });

        return fileWords
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

}
