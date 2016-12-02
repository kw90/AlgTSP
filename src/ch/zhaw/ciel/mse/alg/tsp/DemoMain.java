package ch.zhaw.ciel.mse.alg.tsp;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import ch.zhaw.ciel.mse.alg.tsp.metaheuristics.GreedyInsertion;
import ch.zhaw.ciel.mse.alg.tsp.metaheuristics.NearestNeighbour;
import ch.zhaw.ciel.mse.alg.tsp.metaheuristics.SimulatedAnnealing;
import ch.zhaw.ciel.mse.alg.tsp.utils.*;

public class DemoMain {




	public static void main(String[] args) throws IOException {

		runSingleTSPInstance("berlin52");
		//runSingleTSPInstance("bier127");
		//runSingleTSPInstance("reseau_suisse");

		//runRandomInstances(20);

	}

	private static void runSingleTSPInstance(String instanceName) throws IOException {

		String solutionName = instanceName;

		String pathToInstances = "TSP_Instances";
		String pathToSolutions = "TSP_Solutions";

		String instanceFilenameExtension = ".tsp";
		String solutionFilenameExtension = ".html";

		String pathToInstance = pathToInstances + "/" + instanceName + instanceFilenameExtension;
		String pathToSolution = pathToSolutions + "/" + solutionName + solutionFilenameExtension;

		Problem problem = Problem.load(Paths.get(pathToInstance));

		System.out.println("Loading instance " + instanceName + "...");

		System.out.println("Instance has " + problem.getPoints().size() + " points.");

		System.out.println("Start generating a solution...");
		//for (int i = 0; i < 100; i++) {
		//List<Point> solution = new RandomInsertion().solve(instance);
		//List<Point> solution = new NearestNeighbour().solve(problem);
		//List<Point> solution = new GreedyInsertion().solve(instance);
		List<Point> solution = new SimulatedAnnealing().solve(problem);

		System.out.println("Solution for " + instanceName + " has length: " + Utils.euclideanDistance2D(solution));
		System.out.println();
		Printer.writeToSVG(problem, solution, Paths.get(pathToSolution));
		//}

	}


	private static void runRandomInstances(int numberOfInstances) throws IOException {
		//Run algorithms on some randomly generated instances

		String pathToSolutions = "RandomInstances_Solutions";
		List<Point> solution = null;

		System.out.println("Generating and solving " + numberOfInstances + " random TSP instances...");
		for (int i = 0; i < numberOfInstances; i++){

			//Generate a random instance.
			int numPoints = 100 * i + 50;
			Problem randomProblem = Utils.getRandomProblem("" + i, "random instance with " + numPoints + " points.", numPoints);
			System.out.println("   Random instance number " + i + " generated with " + numPoints + " points.");


			//Get a random path (most likely quite long)
			solution = Utils.getAllPermutations(randomProblem.getPoints()).next();
			Printer.writeToSVG(randomProblem, solution, Paths.get(pathToSolutions, i + "_randomPath.html"));
			System.out.println("        Random Path has length: " + Utils.euclideanDistance2D(solution));

//			//Get the solution obtained with the nearest neighbor heuristic.
			solution = new NearestNeighbour().solve(randomProblem);
			Printer.writeToSVG(randomProblem, solution, Paths.get(pathToSolutions, i + "_nearest.html"));
			System.out.println("        Nearest Neighbor solution has length: " + Utils.euclideanDistance2D(solution));

			//Get the solution obtained with the greedy insertion heuristic.
			solution = new GreedyInsertion().solve(randomProblem);
			Printer.writeToSVG(randomProblem, solution, Paths.get(pathToSolutions, i + "_greedy.html"));
			Printer.writeToSVG(randomProblem, solution, Paths.get(pathToSolutions, i + "_greedy.html"));
			System.out.println("        Greedy Insertion solution has length: " + Utils.euclideanDistance2D(solution));

			System.out.println();

		}
	}


}
