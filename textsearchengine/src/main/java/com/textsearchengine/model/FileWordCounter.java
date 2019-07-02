package com.textsearchengine.model;

/**
 * Keeps information about occurrences of a word in a file.
 */
public class FileWordCounter {
    private String filename;
    private String word;
    private int wordCount;
    private int rowIndex;
    private int coloumIndex;

    public FileWordCounter(String filename, String word, int wordCount) {
        this.filename = filename;
        this.word = word;
        this.wordCount = wordCount;
    }

    public String getFilename() {
        return filename;
    }

    public String getWord() {
        return word;
    }

    public int getWordCount() {
        return wordCount;
    }

    public FileWordCounter incrementWordCount() {
        this.wordCount++;
        return this;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColoumIndex() {
        return coloumIndex;
    }

    public void setColoumIndex(int coloumIndex) {
        this.coloumIndex = coloumIndex;
    }



}
