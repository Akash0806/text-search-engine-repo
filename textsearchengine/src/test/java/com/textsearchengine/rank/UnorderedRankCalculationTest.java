package com.textsearchengine.rank;

import com.textsearchengine.contsant.SearchTextConstant;
import com.textsearchengine.model.FileWordCounter;
import com.textsearchengine.search.SearchStrategy;
import org.hamcrest.core.IsAnything;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UnorderedRankCalculationTest {
    @Mock
    SearchStrategy searchStrategy;

    UnorderedRankCalculation unorderedRankCalculation;
    int searchWordCount = 6;

    @Before
    public void setup(){
        final double rankPerWord = SearchTextConstant.MAX_RANKING_SCALE / searchWordCount;
        unorderedRankCalculation = new UnorderedRankCalculation(searchStrategy,rankPerWord);
    }

    @Test
    public void calculate_WhenFoundWords(){
        java.util.Map<String, List<FileWordCounter>> index = new HashMap<>();
        List<FileWordCounter> fileWordCounters=  getFileWordCounterList();
        index.put("to", fileWordCounters );
        //index.put("to")
        Map<String, Long> wordAndRank = new HashMap<>();
        wordAndRank.put("Test1",2l);
        Map<String, Long> searchWords = getStringLongMap();
        Mockito.when(searchStrategy.findWordAndRank(index,searchWords)).thenReturn(wordAndRank);

        Map<String, Long> result = unorderedRankCalculation.calculate(index,searchWords,searchWordCount);
        assertThat("Size should be one",result.size()==1);
    }
    private List<FileWordCounter>  getFileWordCounterList() {
        List<FileWordCounter> fileWordCounters = new ArrayList<>();
        FileWordCounter fileWordCounter = new FileWordCounter("Test1", "to", 2);
        fileWordCounters.add(fileWordCounter);
        return fileWordCounters;
    }
    private Map<String, Long> getStringLongMap() {
        Map<String, Long> searchWords = new HashMap<>();
        searchWords.put("to",2l);
        searchWords.put("be",2l);
        searchWords.put("or",1l);
        searchWords.put("not",1l);
        return searchWords;
    }
}
