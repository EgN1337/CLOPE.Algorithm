package model;

import java.util.*;

/**
 * Class that is describing cluster
 */
public class Cluster {

    /**
     * Transaction's collection
     */
    private List<Transaction> transactionList = new ArrayList<>();

    /**
     * Cluster object
     * @param transaction - attribute of each cluster btw
     */
    public Cluster(Transaction transaction){
        this.transactionList.add(transaction);
    }

    /**
     * Output cluster map
     * @return - map of clusters
     */
    public Map<String, Integer> getClusterMap(){
        Map<String, Integer> clusterMap = new HashMap<>();
        for (Transaction transaction : transactionList){
            for (String string : transaction.getAllTransactionItems()){
                if (clusterMap.containsKey(string)){
                    clusterMap.put(string, clusterMap.get(string) + 1);
                } else {
                    clusterMap.put(string, 1);
                }
            }
        }
        return clusterMap;
    }

    /**
     * Getting cluster height method
     * @return cluster height
     */
    public double getHeight(){
        double clusterHeight = 0;
        for (Integer height : getClusterMap().values()) {
            if (clusterHeight < height) {
                clusterHeight = height;
            }
        }
        return clusterHeight;
    }

    /**
     * Getting cluster width method
     * @return cluster width
     */
    public double getWidth(){
        return getClusterMap().size();
    }

    /**
     * Adding transaction method
     * @param transaction - transaction
     */
    public void addTransactionToCluster(Transaction transaction){
        transactionList.add(transaction);
    }

    /**
     * Removing transaction method
     * @param transaction - transaction
     */
    public void removeTransactionFromCluster(Transaction transaction){
        transactionList.remove(transaction);
    }

    /**
     * Getting amount of transaction method
     * @return Amount of transaction
     */
    public double getTransactionsAmount(){
        return transactionList.size();
    }

    @Override
    public String toString() {
        return "\n" + "{" + Arrays.toString(transactionList.toArray()) + "}";
    }

}
