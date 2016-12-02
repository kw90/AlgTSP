package ch.zhaw.ciel.mse.alg.tsp.metaheuristics;

import ch.zhaw.ciel.mse.alg.tsp.utils.*;
import java.util.*;

/**
 * Created by Kai Waelti on 17.11.16.
 */
public class NearestNeighbour implements ISolver {

    private static TreeSet<Point> unvisitedPoints;

    public List<Point> solve(Problem problem) {
        Point current = null;
        List<Point> pointsList = new ArrayList<>(problem.getPoints());
        unvisitedPoints = new TreeSet<>(Point::compareTo);
        unvisitedPoints.addAll(pointsList);

        current = unvisitedPoints.first();
        pointsList.add(current);
        unvisitedPoints.remove(current);

        //Get closest point from current
        while (!unvisitedPoints.isEmpty()) {
            Point next = GetClosestPoint(current);
            current = next;
            pointsList.add(current);
            unvisitedPoints.remove(current);
        }

        return pointsList;
    }

    private static Point GetClosestPoint(Point current) {
        double lowestDistance = Double.POSITIVE_INFINITY;
        Point lowestDistancePoint = null;

        for (Point next : unvisitedPoints) {
            double distance = Utils.euclideanDistance2D(current, next);
            if (distance < lowestDistance) {
                lowestDistance = distance;
                lowestDistancePoint = next;
            }
        }

        return lowestDistancePoint;
    }
}
