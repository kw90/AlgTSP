package ch.zhaw.ciel.mse.alg.tsp.metaheuristics;

import ch.zhaw.ciel.mse.alg.tsp.utils.Point;
import ch.zhaw.ciel.mse.alg.tsp.utils.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by kW90 on 27.11.2016.
 */
public interface ISolver {
    public List<Point> solve(Problem problem);
}
