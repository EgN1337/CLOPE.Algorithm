package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that is describing transaction
 */
public class Transaction {

    /**
     * Transaction components
     */
    private List<String> items = new ArrayList<>();

    /**
     * Transaction object
     * @param strings - param that is needed for the process of adding components into transaction
     */
    public Transaction(String... strings) {
        for (String s : strings) {
            this.items.add(s);
        }
    }

    /**
     * Getting all transaction items method
     * @return - transaction items
     */
    public List<String> getAllTransactionItems(){
        return items;
    }

    @Override
    public String toString() {
        return Arrays.toString(items.toArray());
    }

}
