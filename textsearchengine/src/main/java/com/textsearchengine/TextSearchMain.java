package com.textsearchengine;

import com.textsearchengine.contsant.SearchTextConstant;
import com.textsearchengine.cache.FileAndWordCacheIndex;
import com.textsearchengine.model.FileWordCounter;
import com.textsearchengine.rank.UnorderedRankCalculation;
import com.textsearchengine.reader.FactoryReader;
import com.textsearchengine.search.SearchStrategy;
import com.textsearchengine.search.UnorderedWordRankSearch;
import com.textsearchengine.util.TestSearchUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextSearchMain {
   static Map<String, SearchStrategy> searchStrategyMap = new HashMap<>();

    static{
        searchStrategyMap.put(SearchTextConstant.UNORDERED_SREACH,new UnorderedWordRankSearch());
    }
    public static void main(String[] args) {

        if (args.length == 0 ) {
            throw new IllegalArgumentException( "No directory given to index." );
        }
        final File indexableDirectory = new File(args[0]);

        Map<String, List<FileWordCounter>> index = null;
        try {
           index  = new FileAndWordCacheIndex(new FactoryReader()).index(Files.list(indexableDirectory.toPath()),"txt");
        } catch (IOException e) {
            System.out.println("Loading files failed");
            e.printStackTrace();
            System.exit(1);
        }

        Scanner keyboard = new Scanner(System. in );
        while (true) {
            System.out.print(" search> ");
            final String line = keyboard.nextLine();
            if (":quit".equals(line)) {
                System.out.println("Bye!");
                System.exit(0);
            }
            String[] words = line.split(" ");
            int searchWordCount = words.length;
            Map<String, Long> searchWords = Arrays
                    .stream(words)
                    .map(TestSearchUtil::normalizeWord)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            SearchStrategy searchStrategy  = searchStrategyMap.get(SearchTextConstant.UNORDERED_SREACH);
            final double rankPerWord = SearchTextConstant.MAX_RANKING_SCALE / searchWordCount;
            Map<String, Long> result = new UnorderedRankCalculation(searchStrategy,rankPerWord).calculate(index, searchWords, searchWordCount);
            TestSearchUtil.printResult(result);
        }
    }
}



