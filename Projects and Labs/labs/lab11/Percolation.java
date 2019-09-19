// import edu.princeton.cs.algs4.QuickFindUF;
// import edu.princeton.cs.algs4.QuickUnionUF;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;


public class Percolation {
    public boolean[][] grid;
    private int numberopen = 0;
    private int columnsandrows = 0;
    private WeightedQuickUnionUF array;
    private int virtualTop;
    private int virtualBottom;


    /* Creates an N-by-N grid with all sites initially blocked. */
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N is less that 0");
        }
        grid = new boolean[N][N];
        columnsandrows = N;
        array = new WeightedQuickUnionUF(N * N + 2);
        virtualTop = N * N;
        virtualBottom = N * N + 1;
    }

    /* Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (!grid[row][col]) {
            grid[row][col] = true;
            numberopen += 1;
            ArrayList<Integer> a = adjacentSquares(row, col);
            for (int i : a) {
                int toUnion = xyTo1D(row, col);
                array.union(i, toUnion);
            }
            if (row == 0) {
                array.union(xyTo1D(row, col), virtualTop);
            }
            if (row == columnsandrows - 1) {
                array.union(xyTo1D(row, col), virtualBottom);
            }
        }
    }

    /* Returns true if the site at (row, col) is open. */
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }

    /* Returns true if the site (row, col) is full. */
    public boolean isFull(int row, int col) {
        return array.connected(xyTo1D(row, col), virtualTop);
    }

    /* Returns the number of open sites. */
    public int numberOfOpenSites() {
        return numberopen;
    }

    /* Returns true if the system percolates. */
    public boolean percolates() {
        return array.connected(virtualBottom, virtualTop);
    }

    /* Converts row and column coordinates into a number. This will be helpful
       when trying to tie in the disjoint sets into our NxN grid of sites. */
    public int xyTo1D(int row, int col) {
        if (row == 0) {
            return col;
        } else {
            return row * columnsandrows + col;
        }
    }

    /* Returns true if (row, col) site exists in the NxN grid of sites.
       Otherwise, return false. */
    public boolean valid(int row, int col) {
        if (row <= columnsandrows - 1 && col <= columnsandrows - 1 && row >= 0 && col >= 0) {
            return true;
        }
        return false;
    }

    // returns the adjacent squares that are valid/are open
    public ArrayList<Integer> adjacentSquares(int row, int col) {
        ArrayList<Integer> a = new ArrayList<>();
        if (valid(row, col - 1) && grid[row][col - 1]) {
            a.add(xyTo1D(row, col - 1));
        }
        if (valid(row, col + 1) && grid[row][col + 1]) {
            a.add(xyTo1D(row, col + 1));
        }
        if (valid(row - 1, col) && grid[row - 1][col]) {
            a.add(xyTo1D(row - 1, col));
        }
        if (valid(row + 1, col) && grid[row + 1][col]) {
            a.add(xyTo1D(row + 1, col));
        }
        return a;
    }
}
