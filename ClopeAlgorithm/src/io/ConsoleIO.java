package io;

import interfaces.Reader;
import interfaces.Writer;
import model.Cluster;
import model.Transaction;

import java.util.List;

/**
 * Implementing class for adapting output result in console
 */
public class ConsoleIO implements Reader, Writer {

    @Override
    public List<Transaction> read() {
        return null;
    }

    @Override
    public void write(List<Cluster> clusterList) {
        System.out.println(clusterList.toString());
    }
}
