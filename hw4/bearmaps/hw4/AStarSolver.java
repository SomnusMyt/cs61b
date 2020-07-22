package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private LinkedList<Vertex> solution = new LinkedList<>();;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;
    private final double INF = Double.POSITIVE_INFINITY;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        ArrayHeapMinPQ<Vertex> PQ = new ArrayHeapMinPQ<>();
        Map<Vertex, Double> distToStart = new HashMap<>();
        Map<Vertex, Double> distToEnd = new HashMap<>();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();

        Stopwatch sw = new Stopwatch();
        distToStart.put(start, 0.0);
        PQ.add(start, distToStart.get(start));

        while (PQ.size() != 0) {
            if (PQ.getSmallest().equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = distToStart.get(end);

                Vertex temp = PQ.getSmallest();
                solution.addFirst(temp);
                while (!temp.equals(start)) {
                    solution.addFirst(edgeTo.get(temp));
                    temp = edgeTo.get(temp);
                }
                explorationTime = sw.elapsedTime();
                return;
            }

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(PQ.removeSmallest());
            numStatesExplored++;

            explorationTime = sw.elapsedTime();
            if (explorationTime > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solutionWeight = 0;
                solution = new LinkedList<>();
                return;
            }

            for (WeightedEdge<Vertex> edge : neighborEdges) {
                Vertex p = edge.from();
                Vertex q = edge.to();
                double w = edge.weight();

                if (!distToStart.containsKey(q)) {
                    distToStart.put(q, INF);
                }

                if (!distToEnd.containsKey(q)) {
                    distToEnd.put(q, input.estimatedDistanceToGoal(q, end));
                }

                if (distToStart.get(p) + w < distToStart.get(q)) {
                    distToStart.put(q, distToStart.get(p) + w);
                    edgeTo.put(q, p);

                    if (PQ.contains(q)) {
                        PQ.changePriority(q, distToStart.get(q) + distToEnd.get(q));
                    }
                    else {
                        PQ.add(q, distToStart.get(q) + distToEnd.get(q));
                    }

                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        solution = new LinkedList<>();
        solutionWeight = 0;
        explorationTime = sw.elapsedTime();
    }

    public SolverOutcome outcome() {
        return outcome;
    }
    public List<Vertex> solution() {
        return solution;
    }
    public double solutionWeight() {
        return solutionWeight;
    }
    public int numStatesExplored() {
        return numStatesExplored;
    }
    public double explorationTime() {
        return explorationTime;
    }
}
