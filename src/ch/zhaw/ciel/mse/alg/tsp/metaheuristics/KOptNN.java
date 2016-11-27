package ch.zhaw.ciel.mse.alg.tsp.metaheuristics;

import ch.zhaw.ciel.mse.alg.tsp.utils.Point;
import ch.zhaw.ciel.mse.alg.tsp.utils.Problem;

import java.util.List;

/**
 * Created by kW90 on 18.11.2016.
 */
public class KOptNN {

    public static List<Point> solve(Problem problem) {
        List NNSol = NearestNeighbour.solve(problem);
        return null;
    }
}
