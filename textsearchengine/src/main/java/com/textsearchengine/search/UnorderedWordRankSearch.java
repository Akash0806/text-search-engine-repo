package com.textsearchengine.search;

import com.textsearchengine.model.FileWordCounter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Below class is used to perform search operation on index
 */
public class UnorderedWordRankSearch implements SearchStrategy {
    public Map<String, Long> findWordAndRank(Map<String, List<FileWordCounter>> index, Map<String, Long> searchWords) {
        Map<String, Long> wordsWithRank = new HashMap<>();
        if (index != null && index.size() > 0) {
            index.entrySet()
                    .stream()
                    .filter(es -> searchWords.containsKey(es.getKey()))
                    .map(Map.Entry::getValue)
                    .flatMap(Collection::stream)
                    .forEach(e -> {
                        long currentWordsMatched = Math.min(e.getWordCount(), searchWords.get(e.getWord()));
                        Long previousWordsMatched = wordsWithRank.getOrDefault(e.getFilename(), 0L);

                        wordsWithRank.put(e.getFilename(), currentWordsMatched + previousWordsMatched);
                    });
        }
        return wordsWithRank;
    }
}
