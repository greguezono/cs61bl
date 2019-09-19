import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {
    @Test
    public void initializeTest() {
        UnionFind a = new UnionFind(6);
        a.union(1, 3);
        a.union(2, 4);
        a.union(4, 5);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a.array[i]);
        }
    }

    @Test
    public void testConnected() {
        UnionFind a = new UnionFind(6);
        assertEquals(a.connected(3, 4), false);
        a.union(1, 2);
        a.union(1, 3);
        assertEquals(a.connected(2, 3), true);
        a.union(3, 4);
        assertEquals(a.connected(2, 4), true);
    }

    @Test
    public void testParent() {
        UnionFind a = new UnionFind(6);
        a.union(1, 2);
        a.union(1, 3);
        a.union(3, 4);
        int parent1 = a.parent(4);
        assertEquals(parent1, 3);
        int parent2 = a.parent(1);
        assertEquals(parent2, -3);
    }

    @Test
    public void testFind() {
        UnionFind a = new UnionFind(6);
        a.union(2, 3);
        a.union(3, 4);
        a.union(2,1);
        int find1 = a.find(3);
        assertEquals(find1, 3);
        int find2 = a.find(1);
        assertEquals(find2, 3);
        int find3 = a.find(4);
        assertEquals(find3, 3);
    }

    @Test
    public void compTest() {
        UnionFind a = new UnionFind(10);
        a.union(2, 3);
        a.union(4, 5);
        a.union(2,5);
        a.union(5,6);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a.array[i]);
        }
    }
}
