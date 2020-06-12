package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int top;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF uf;
    private int numOfOpenSites = 0;
    private int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        grid = new boolean[N][N];
        top = 0;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);

    }
    private int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOfOpenSites++;
        }
        if (row == 0) {
            uf.union(xyTo1D(row, col), top);
        }
        if (row == size - 1) {
            uf.union(xyTo1D(row, col), bottom);
        }
        for (int[] dir : dirs) {
            int temprow = row + dir[0];
            int tempcol = col + dir[1];
            if (tempcol >= size || tempcol < 0 || temprow >= size || temprow < 0) {
                continue;
            }
            if (isOpen(temprow, tempcol)) {
                uf.union(xyTo1D(row, col), xyTo1D(temprow, tempcol));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return uf.connected(xyTo1D(row, col), top);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }
    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
