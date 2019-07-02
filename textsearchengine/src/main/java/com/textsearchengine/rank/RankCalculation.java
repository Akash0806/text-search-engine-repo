package com.textsearchengine.rank;

import com.textsearchengine.model.FileWordCounter;

import java.util.List;
import java.util.Map;

public interface RankCalculation {
    Map<String, Long> calculate(Map<String, List<FileWordCounter>> index, Map<String, Long> searchWords, int searchWordCount);
}
