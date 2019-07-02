package com.textsearchengine.rank;

import com.textsearchengine.model.FileWordCounter;
import com.textsearchengine.search.SearchStrategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.textsearchengine.contsant.SearchTextConstant.MAX_RESULTS;

public class UnorderedRankCalculation implements RankCalculation {

    private SearchStrategy searchStrategy;
    private double rankStrategy;

    public UnorderedRankCalculation(SearchStrategy searchStrategy,double rankStrategy){
        this.searchStrategy = searchStrategy;
        this.rankStrategy = rankStrategy;
    }

    public  Map<String, Long> calculate(Map<String, List<FileWordCounter>> index, Map<String, Long> searchWords, int searchWordCount) {
        Map<String, Long> foundWords = searchStrategy.findWordAndRank(index, searchWords);
        return sortAndNormalizeRank(foundWords);
    }

    private  Map<String, Long> sortAndNormalizeRank(Map<String, Long> words) {
        return words.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(MAX_RESULTS)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Math.round(e.getValue() * rankStrategy),
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }
}
