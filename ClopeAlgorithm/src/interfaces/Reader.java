package interfaces;

import model.Transaction;

import java.util.List;

/**
 * Main interface of reading begin condition
 */
public interface Reader {
    List<Transaction> read();
}
