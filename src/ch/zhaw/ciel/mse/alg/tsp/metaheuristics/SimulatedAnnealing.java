package ch.zhaw.ciel.mse.alg.tsp.metaheuristics;

import ch.zhaw.ciel.mse.alg.tsp.utils.Point;
import ch.zhaw.ciel.mse.alg.tsp.utils.Problem;
import ch.zhaw.ciel.mse.alg.tsp.utils.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kW90 on 27.11.2016.
 */
public class SimulatedAnnealing implements ISolver {

    private double temperature = 1000000;

    private static final double COOLING_RATE = 0.003;

    private double acceptanceProbability(double energy, double newEnergy, double temperature) {
        if (newEnergy < energy) return 1.0;

        return Math.exp((energy - newEnergy) / temperature);
    }

    @Override
    public List<Point> solve(Problem problem) {
        List<Point> points = new ArrayList<>(problem.getPoints());
        Tour currentSolution = new Tour(points);

        currentSolution.generateIndividual(points);

        Tour bestTour = new Tour(currentSolution.getTour());

        while (temperature > 1) {
            Tour newSolution = new Tour(currentSolution.getTour());

            int tourPosition1 = (int) (newSolution.getTour().size() * Math.random());
            int tourPosition2 = (int) (newSolution.getTour().size() * Math.random());

            Point pointSwap1 = newSolution.getPoint(tourPosition1);
            Point pointSwap2 = newSolution.getPoint(tourPosition2);

            newSolution.setPoint(tourPosition2, pointSwap1);
            newSolution.setPoint(tourPosition1, pointSwap2);

            double currentEnergy = currentSolution.getDistance();
            double neighbourEnergy = newSolution.getDistance();

            if (acceptanceProbability(currentEnergy, neighbourEnergy, temperature) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            if (currentSolution.getDistance() < bestTour.getDistance()) {
                bestTour = new Tour(currentSolution.getTour());
            }

            temperature *= 1 - COOLING_RATE;

        }

        return bestTour.getTour();
    }
}
