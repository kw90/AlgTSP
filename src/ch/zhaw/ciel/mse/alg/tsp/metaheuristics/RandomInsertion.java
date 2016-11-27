package ch.zhaw.ciel.mse.alg.tsp.metaheuristics;

import ch.zhaw.ciel.mse.alg.tsp.utils.Instance;
import ch.zhaw.ciel.mse.alg.tsp.utils.Point;
import ch.zhaw.ciel.mse.alg.tsp.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kai Waelti on 17.11.16.
 */
public class RandomInsertion {

    public static List<Point> solve(Instance instance) {
        Point[] points = instance.getPoints().toArray(new Point[instance.getPoints().size()]);

        List<Point> pointsList = new ArrayList<>();
        List<Integer> rndmInts = new ArrayList<>();

        while (rndmInts.size() < points.length) {
            int rndm = ThreadLocalRandom.current().nextInt(points.length);
            if (!rndmInts.contains(rndm)) {
                rndmInts.add(rndm);
            }
        }

        for (int rdmint : rndmInts) {
            if (!pointsList.contains(points[rdmint])) {
                pointsList.add(points[rdmint]);
            }
        }

        return pointsList;
    }
}
