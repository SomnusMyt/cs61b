public class BubbleGrid {
    private final int[][] grid;
    private final int rowNum;
    private final int columnNum;
    private final int ceiling = 0;
    private final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        columnNum = grid[0].length;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        UnionFind uf = new UnionFind(rowNum * columnNum + 1);

        for (int[] dart : darts) {
            if (grid[dart[0]][dart[1]] == 1) {
                grid[dart[0]][dart[1]] = 2;
            }
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (grid[i][j] == 1) {
                    unionBubbles(i, j, grid, uf);
                }
            }
        }
        int[] res = new int[darts.length];
        int count = uf.sizeOf(ceiling);

        for (int i = darts.length - 1; i >= 0; i--) {
            int temprow = darts[i][0];
            int tempcol = darts[i][1];
            if (grid[temprow][tempcol] == 2) {
                unionBubbles(temprow, tempcol, grid, uf);
                grid[temprow][tempcol] = 1;
            }

            int newcount = uf.sizeOf(ceiling);
            res[i] = newcount > count ? newcount - count - 1 : 0;
            count = newcount;
        }
        return res;
    }

    public void unionBubbles(int row, int column, int[][] grid, UnionFind uf) {
        if (row == 0) {
            uf.union(to1D(row, column), ceiling);
        }

        for (int[] dir : dirs) {
            int temprow = row + dir[0];
            int tempcolumn = column + dir[1];
            if (temprow < 0 || temprow == rowNum || tempcolumn < 0 || tempcolumn == columnNum || grid[temprow][tempcolumn] != 1) {
                continue;
            }
            uf.union(to1D(row, column), to1D(temprow, tempcolumn));
        }
    }

    public int to1D(int row, int column) {
        return row * columnNum + column + 1;
    }
}
