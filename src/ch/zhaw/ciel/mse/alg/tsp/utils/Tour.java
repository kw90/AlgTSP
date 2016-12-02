package ch.zhaw.ciel.mse.alg.tsp.utils;

import ch.zhaw.ciel.mse.alg.tsp.metaheuristics.NearestNeighbour;

import java.util.*;

/**
 * Created by kW90 on 27.11.2016.
 */
public class Tour {

    private ArrayList<Point> tour = new ArrayList<>();

    private int distance = 0;

    public Tour(List<Point> tour) {
        ArrayList<Point> points = new ArrayList<>(tour);
        ArrayList clonedPoints = (ArrayList) points.clone();
        this.tour.addAll(clonedPoints);
    }

    public List<Point> getTour() {
        return tour;
    }

    public void generateIndividual(List<Point> problemPoints) {
        ArrayList<Point> points = new ArrayList<>(problemPoints);
        tour = new ArrayList<>(points);
        for (int i = 0; i < points.size(); i++) {
            setPoint(i, points.get(i));
        }
        Collections.shuffle(tour);
    }

    public Point getPoint(int tourPosition) {
        return tour.get(tourPosition);
    }

    public void setPoint(int tourPosition, Point point) {
        tour.set(tourPosition, point);
        distance = 0;
    }

    public double getDistance() {
        if (distance == 0) {
            int tourDistance = 0;

            for (int pointIndex = 0; pointIndex < tour.size(); pointIndex++) {
                Point fromPoint = getPoint(pointIndex);
                Point toPoint = null;

                if (pointIndex+1 < tour.size()) {
                    toPoint = getPoint(pointIndex+1);
                }
                else {
                    toPoint = getPoint(0);
                }

                tourDistance += Utils.euclideanDistance2D(fromPoint, toPoint);
            }

            distance = tourDistance;
        }
        return distance;
    }

}
