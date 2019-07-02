package com.textsearchengine.search;

import com.textsearchengine.model.FileWordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UnorderedWordRankSearchTest {

    @InjectMocks
    private UnorderedWordRankSearch accountServiceImpl;

    @Test
    public void findWordAndRank_WhenIndexIsNull(){
        java.util.Map<String, List<FileWordCounter>> index =null;
        Map<String, Long> searchWords = new HashMap<>();
        Map<String, Long> wordAndRank = accountServiceImpl.findWordAndRank(index,searchWords);
        assertThat("Size should be zero",wordAndRank.size()==0);
    }
    @Test
    public void findWordAndRank_WhenIndexIsEmpty(){
        java.util.Map<String, List<FileWordCounter>> index = new HashMap<>();
        Map<String, Long> searchWords = new HashMap<>();
        Map<String, Long> wordAndRank = accountServiceImpl.findWordAndRank(index,searchWords);
        assertThat("Size should be zero When its empty",wordAndRank.size()==0);
    }

    @Test
    public void findWordAndRank_WhenIndexAndSearchNotEmpty(){
        java.util.Map<String, List<FileWordCounter>> index = new HashMap<>();
        List<FileWordCounter> fileWordCounters=  getFileWordCounterList();
        index.put("to", fileWordCounters );
        FileWordCounter fileWordCounter2 = new FileWordCounter("Test2", "not", 1);
        fileWordCounters.add(fileWordCounter2);
        index.put("not",  getFileWordCounterList());
        Map<String, Long> searchWords = getStringLongMap();
        Map<String, Long> wordAndRank = accountServiceImpl.findWordAndRank(index,searchWords);
        assertThat("Size should be two",wordAndRank.size()==2);
        assertThat(wordAndRank.get("Test1"), is(4l));
        assertThat(wordAndRank.get("Test2"), is(1l));
    }

    private Map<String, Long> getStringLongMap() {
        Map<String, Long> searchWords = new HashMap<>();
        searchWords.put("to",2l);
        searchWords.put("be",2l);
        searchWords.put("or",1l);
        searchWords.put("not",1l);
        return searchWords;
    }

    private List<FileWordCounter>  getFileWordCounterList() {
        List<FileWordCounter> fileWordCounters = new ArrayList<>();
        FileWordCounter fileWordCounter = new FileWordCounter("Test1", "to", 2);
        fileWordCounters.add(fileWordCounter);
        return fileWordCounters;
    }
}
