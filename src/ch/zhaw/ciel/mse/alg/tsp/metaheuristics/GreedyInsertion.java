package ch.zhaw.ciel.mse.alg.tsp.metaheuristics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.zhaw.ciel.mse.alg.tsp.utils.Point;
import ch.zhaw.ciel.mse.alg.tsp.utils.Problem;
import ch.zhaw.ciel.mse.alg.tsp.utils.Utils;

/**
 * 
 * @author Marek Arnold (arnd@zhaw.ch)
 * 
 * The greedy insertion meta heuristic to find a solution for a given TSP.
 * The tour starts at the {@link Point} with the lowest id and the points are inserted by ascending id.
 * For positions with an equal cost, the first such position is taken.
 */
public class GreedyInsertion implements ISolver {
	/**
	 * Solve the given TSP using greedy insertion.
	 * @param problem The problem to solve.
	 * @return A list of points. Each point in the problem appears exactly once.
	 */
	public List<Point> solve(Problem problem) {
		Point[] points = problem.getPoints().toArray(new Point[problem.getPoints().size()]);
		Arrays.sort(points, (p1, p2) -> Integer.compare(p1.getId(), p2.getId()));
		
		//Array with the indices of the next nodes
		int[] nextIndices = new int[points.length];
		
		//Initial partial tour 0 -> 1 -> 0
		nextIndices[0] = 1;
		
		//Find the best position to insert for each remaining point
		for(int i = 2; i < points.length; i++){
			double lowestDistanceIncrease = Double.POSITIVE_INFINITY;
			int lowestDistanceIncreaseIdx = -1;
			
			for(int j = 0; j < i; j++){
				//Increased cost of tour if point i is inserted in place j
				double distanceIncrease = Utils.euclideanDistance2D(points[j], points[i]) + Utils.euclideanDistance2D(points[i], points[nextIndices[j]]) - Utils.euclideanDistance2D(points[j], points[nextIndices[j]]);
				if (distanceIncrease < lowestDistanceIncrease){
					lowestDistanceIncrease = distanceIncrease;
					lowestDistanceIncreaseIdx = j;
				}
			}
			
			nextIndices[i] = nextIndices[lowestDistanceIncreaseIdx];
			nextIndices[lowestDistanceIncreaseIdx] = i;
		}
		
		//Walk along next indices to build solution.
		List<Point> solution = new ArrayList<>(points.length);
		int j = 0;
		for(int i = 0; i < points.length; i++){
			solution.add(points[j]);
			j = nextIndices[j];
		}
		
		return solution;
	}

}
