package run;

import io.ConsoleIO;
import io.FileIO;
import model.Clope;
import model.Cluster;

import java.util.ArrayList;
import java.util.List;

public class ClopeAlgorithmRunner {

    /**
     * Constants of path and repulsion value (can be changed)
     */
    private static final String FILE_PATH = "data.txt";
    private static final String REPULSION_VALUE = "0.4";

    /**
     * The main point of CLOPE Algorithm program
     * @param attr - attributes
     */
    public static void main(String[] attr){
        //List<Cluster> clusterList = new ArrayList<>();
        new ConsoleIO().write(new Clope().ClopeInitialization(new FileIO(FILE_PATH).read(), Double.parseDouble(REPULSION_VALUE)));
        //new ConsoleIO().write(new Clope().ClopeAlgorithm(new FileIO(FILE_PATH).read(), Double.parseDouble(REPULSION_VALUE)));
    }
}
