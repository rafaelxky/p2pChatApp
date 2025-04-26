package io.sourceWare.Tests;

import io.sourceWare.program.client.model.file_handler.FileHandler;

import java.io.File;
import java.util.Objects;

public class FileTests {
    public static final String url = "Program/src/io/sourceWare/Tests/files/test.txt";
    public static void main(String[] args) {
        FileHandler.setFilePath(url);
        System.out.println("Read and rewrite file test: " + testReadAndRewrite() + " - " + (Objects.equals(FileHandler.getCurrentLine(), "Hello World") ? "success ✅" : "failed ❌" ));
        System.out.println("Write to file test: " + testWritingToFile() + " - " + (testWritingToFile().equals("Hello World") ? "success ✅" : "failed ❌" ));
    }

    public static String testReadAndRewrite(){
        FileHandler.rewriteFile("Hello World");
        FileHandler.readFile();
        return FileHandler.readNextLine();
    }

    public static String testWritingToFile(){
        FileHandler.writeToFile("Hello");
        FileHandler.writeToFile(" World");
        FileHandler.readFile();
        return FileHandler.readNextLine();
    }

}
