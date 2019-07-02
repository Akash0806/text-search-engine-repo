package com.textsearchengine.reader;

public class FactoryReader {
    public Reader getReaderImplementation(String type){
        if(type == null){
            return null;
        }
        if(type.equalsIgnoreCase("txt")){
            return new TextFileReader();

        }
        /**Scalability  : based on file type condition can be added */
        return null;
    }
}
