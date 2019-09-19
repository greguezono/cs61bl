import java.util.ArrayList;

public class UnionFind {

    public int[] array;
    public int length;

    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        array = new int[N];
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
        length = N;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if (array[v] < 0) {
            return -array[v];
        } else {
            int pointer = array[v];
            while (pointer > 0) {
                pointer = array[pointer];
            }
            return -pointer;
        }
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (array[v] < 0) {
            return -array[v];
        }
        return array[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid vertices are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || v >= length) {
            throw new IllegalArgumentException("Illegal vertex passed into function");
        } else if (array[v] < 0) {
            return v;
        } else {
            ArrayList<Integer> a = new ArrayList<>();
            int pointer = array[v];
            a.add(v);
            while (array[pointer] > 0) {
                a.add(pointer);
                pointer = array[pointer];
            }
            for (int i : a) {
                array[i] = pointer;
            }
            return pointer;
        }
    }

    /* Connects two elements V1 and V2 together. V1 and V2 can be any element,
       and a union-by-size heuristic is used. If the sizes of the sets are
       equal, tie break by connecting V1's root to V2's root. Union-ing a vertex
       with itself or vertices that are already connected should not change the
       structure. */
    public void union(int v1, int v2) {
        if (v1 == v2 || connected(v1, v2)) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if (sizeOf(root1) < sizeOf(root2) || sizeOf(root1) == sizeOf(root2)) {
            array[root2] = array[root1] + array[root2];
            array[root1] = root2;
        } else {
            array[root1] = array[root2] + array[root1];
            array[root2] = root1;
        }
    }
}
