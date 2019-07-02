package com.textsearchengine.reader;

import com.textsearchengine.model.FileWordCounter;

import java.nio.file.Path;
import java.util.Map;

public interface Reader {
    Map<String, FileWordCounter> readFile(Path file) ;
}
