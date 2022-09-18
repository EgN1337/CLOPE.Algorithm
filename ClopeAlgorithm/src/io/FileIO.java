package io;

import interfaces.Reader;
import interfaces.Writer;
import model.Cluster;
import model.Transaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementing class for adapting input from file
 */
public class FileIO implements Reader, Writer {

    /**
     * Constant 'SPLIT_ITEM' for rational reading file components
     */
    private static final String SPLIT_ITEM = " ";

    /**
     * File path Variable
     */
    private final String filePath;

    /**
     * FileIO object
     * @param path - Path of 'txt' file extension
     */
    public FileIO(String path){
        filePath = path;
    }

    @Override
    public List<Transaction> read() {
        List<Transaction> items = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(new Transaction(line.split(SPLIT_ITEM)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public void write(List<Cluster> clusterList) {
    }
}
