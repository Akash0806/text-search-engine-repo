package com.textsearchengine.search;

import com.textsearchengine.model.FileWordCounter;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {
   public Map<String, Long> findWordAndRank(Map<String, List<FileWordCounter>> index, Map<String, Long> searchWords);
}
