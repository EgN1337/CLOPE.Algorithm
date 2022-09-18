package interfaces;

import model.Cluster;

import java.util.List;

/**
 * Main interface of writing process
 */
public interface Writer {
    void write(List<Cluster> clusterList);
}
