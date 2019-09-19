import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PercolationTest {
    @Test
    //tested private methods and initializer for Percolation
    public void gridInitializer() {
        Percolation p = new Percolation(3);
        assertEquals(p.grid[0][0], false);
        p.open(0, 0);
        assertEquals(p.grid[0][0], true);
        assertEquals(p.xyTo1D(0, 0), 0);
        assertEquals(p.xyTo1D(2, 2), 8);
        assertEquals(p.xyTo1D(1, 1), 4);
    }

    @Test
    public void adjacentSquares() {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.open(2, 1);
        ArrayList<Integer> a = p.adjacentSquares(2, 2);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
