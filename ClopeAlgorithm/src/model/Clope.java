package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class that is describing considering algorithm
 */
public class Clope {
    /**
     * Set of clusters that is need for completing gathering process
     */
    private static List<Cluster> clusterSET = new ArrayList<>();

    private static int bestCostIndex;

    public static List<Cluster> ClopeInitialization(List<Transaction> transactionList, double repulsion){

        boolean moved;

        clusterSET.add(new Cluster(new Transaction(" ")));
        double maxCost;
        for (Transaction transaction : transactionList){
            Cluster newCluster = new Cluster(transaction);
            clusterSET.add(newCluster);
            maxCost = 0;
            for (Cluster cluster : clusterSET){
                double addingCost = AddCost(cluster, transaction, repulsion);
                if (addingCost > maxCost){
                    maxCost = addingCost;
                    bestCostIndex = clusterSET.indexOf(cluster);
                }
            }
            if (clusterSET.get(bestCostIndex) == null){
                clusterSET.add(new Cluster(null));
            }
            clusterSET.get(bestCostIndex).addTransactionToCluster(transactionList.get(transactionList.indexOf(transaction)));
        }

        do {
            moved = false;
            for (Transaction transaction : transactionList) {
                maxCost = 0;
                Cluster currentCluster = TransactionClusterFinder(clusterSET, transaction);

                assert currentCluster != null;
                double removedCost = RemoveCost(currentCluster, transaction, repulsion);
                clusterSET.add(currentCluster);
                for (Cluster cluster : clusterSET){
                    if (AddCost(cluster, transaction, repulsion) + removedCost > maxCost){
                        maxCost = AddCost(cluster, transaction, repulsion) + removedCost;
                        bestCostIndex = clusterSET.indexOf(cluster);
                    }
                }
                clusterSET.remove(currentCluster);
                if (maxCost > 0){
                    if (!(clusterSET.get(bestCostIndex) == null)){
                        clusterSET.add(clusterSET.get(bestCostIndex));
                    }
                    TransactionMover(currentCluster, clusterSET.get(bestCostIndex), transaction);
                    moved = true;
                }
            }

        } while (!moved);
        return clusterSET;
    }

    public static Cluster TransactionClusterFinder(List<Cluster> clusterList, Transaction transaction){
        for (Cluster cluster : clusterList){

            if (cluster.toString().contains(transaction.toString())){
                return cluster;
            }
        }
        return null;
    }

    public static void TransactionMover(Cluster cluster1, Cluster cluster2, Transaction transaction){
        cluster1.removeTransactionFromCluster(transaction);
        cluster2.addTransactionToCluster(transaction);
    }

    public static double AddCost(Cluster cluster, Transaction transaction, double repulsion){

        double clusterWidth = cluster.getWidth();
        double clusterWidthNew = clusterWidth;
        double clusterSize = cluster.getTransactionsAmount();
        double clusterSizeNew = clusterSize + transaction.getAllTransactionItems().size();
        List<String> transactionObjects = transaction.getAllTransactionItems();
        double clusterTransactionAmount = cluster.getTransactionsAmount();
        double clusterTransactionAmountNew = cluster.getTransactionsAmount() + 1;

        for (String transactionObject : transactionObjects){
            if (!transactionObjects.contains(transactionObject)) {
                clusterWidthNew++;
            }
        }
        return clusterSizeNew * clusterTransactionAmountNew / Math.pow(clusterWidthNew , repulsion) - clusterSize * clusterTransactionAmount / Math.pow(clusterWidth, repulsion);
    }

    public static double RemoveCost(Cluster cluster, Transaction transaction, double repulsion){

        double clusterWidth = cluster.getWidth();
        double clusterWidthNew = clusterWidth;
        double clusterSize = cluster.getTransactionsAmount();
        double clusterSizeNew = clusterSize - transaction.getAllTransactionItems().size();
        List<String> transactionObjects = transaction.getAllTransactionItems();
        double clusterTransactionAmount = cluster.getTransactionsAmount();
        double clusterTransactionAmountNew = cluster.getTransactionsAmount() - 1;

        for (String transactionObject : transactionObjects){
            if (!transactionObjects.contains(transactionObject)) {
                clusterWidthNew--;
            }
        }
        return clusterSizeNew * clusterTransactionAmountNew / Math.pow(clusterWidthNew , repulsion) - clusterSize * clusterTransactionAmount / Math.pow(clusterWidth, repulsion);
    }

}
