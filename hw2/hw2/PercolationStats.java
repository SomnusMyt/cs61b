package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int numberOfExperiments;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException();
        }
        fractions = new double[T];
        numberOfExperiments = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(T);
                int col = StdRandom.uniform(T);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            fractions[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractions);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractions);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return (mean() - 1.96 * stddev() / Math.sqrt(numberOfExperiments));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (mean() + 1.96 * stddev() / Math.sqrt(numberOfExperiments));
    }

}
