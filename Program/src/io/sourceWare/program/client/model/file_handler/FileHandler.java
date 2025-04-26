package io.sourceWare.program.client.model.file_handler;

import java.io.*;

public class FileHandler {
    // anything related to saving in files

    private static String currentLine;
    private static BufferedReader reader = null;
    public static String filePath;

    public static void readFile(String filePath){
        try{
            reader = new BufferedReader(new FileReader(filePath));
         } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void readFile(){
       readFile(filePath);
    }

    /**
     * Reads the next line from the reader, stores it in currentLine and returns it.
     * @return currentLine
     * @throws IOException
     */
    public static String readNextLine() {
        try {
            currentLine = FileHandler.reader.readLine();
            return currentLine;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Getter for current line.
     * Here is where the previously read line will be kept for later use.
     * @return currentLine
     */
    public static String getCurrentLine(){
       return currentLine;
    }

    /**
     * Rewrites data into the specified path.
     * This will rewrite all data on the file
     * @param data The data to write to file
     * @param filePath The path of the file to write to
     */

    public static void rewriteFile(String data, String filePath){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(data);
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rewriteFile(String data){
        rewriteFile(data, filePath);
    }

    /**
     * Writes data into the specified path.
     * Each call to the method will append to previous data on the file
     * @param data The data to write to file
     * @param filePath The path of the file to write to
     */
    public static void writeToFile(String data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(String data) {
        writeToFile(data, filePath);
    }

    /**
    *Splits a given byte array into multiple smaller byte arrays (chunks), each with a
    *maximum size of 1024 bytes. The resulting chunks are stored in a two-dimentional byte
    *array (byte[][]), where each sub-array represents a segment of the original data
    *@param data The original byte array to split.
    *@return A 2D byte array containing the split chunks.
    */
    public static byte[][] splitBytes(byte[] data){
        int chunkSize = 1024;
        int totalChunks = (int) Math.ceil((double) data.length / chunkSize);
        byte[][] result = new byte[totalChunks][];


        for (int i = 0; i < totalChunks; i++) {
            int start = i * chunkSize;
            int lenght = Math.min(chunkSize, data.length - start);
            byte[] chunk = new byte[lenght];
            System.arraycopy(data,start, chunk, 0, lenght);
            result[i] = chunk;
        }
        return  result;
    }

    public static void setFilePath(String filePath) {
        FileHandler.filePath = filePath;
    }

    public static String getFilePath() {
        return filePath;
    }
}
