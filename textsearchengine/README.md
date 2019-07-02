# text-search-engine-repo

A command line driven text search engine for text files.
it reads all the text files in the given directory, builds an in memory representation of the files and their content, and then give a command prompt at which interactive searches can be performed. The search takes the words given on the command prompt and returns a list of the top 10 (max) matching filenames in rank order, giving the rank score against each match.

#Requirements
Java 8
Maven
Development
Build
JAR file
Create JAR file
mvn Clean Package
Run JAR file example
java -jar text-search-engine-1.0-SHAPSHOT.jar filePath
Notes
The specs were a bit ambiguous regarding the process to be implemented.  the specs mention words contained in the file, but doesn't mention to retrieve the position of the words where they have been found, which would be the logic approach.
Current logic is decided the ranking based on word found , there is no order maintain ,However I have added index position to support for order
The ranking strategy may need to undergo modifications and/or extensions in the future. This is why I implemented the Strategy Design Pattern and factory patter to support file reading
The library Data Structures (Ds) have been Map data structure. 
No frameworks have been used . Only Junit and mockito 

Performance Improvement -
1. In future improvement , we could use the multithreading to reading multiple files
2. Even in case for searching and divided search in multiple task 

