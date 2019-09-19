import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;
import java.util.HashSet;

public class Graph implements Iterable<Integer> {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;

    /* Initializes a graph with NUMVERTICES vertices and no Edges. */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    /* Adds a directed Edge (V1, V2) to the graph. */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    /* Adds an undirected Edge (V1, V2) to the graph. */
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, 0);
    }

    /* Adds a directed Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addEdge(int v1, int v2, int weight) {
        LinkedList pointer = adjLists[v1];
        Edge e = new Edge(v1, v2, weight);
        if (pointer == null) {
            pointer.addFirst(e);
        } else {
            if (pointer.contains(e)) {
                pointer.remove(e);
                pointer.addFirst(e);
            } else {
                pointer.addFirst(e);
            }
        }
    }

    /* Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
    }

    /* Returns true if there exists an Edge from vertex FROM to vertex TO.
       Returns false otherwise. */
    public boolean isAdjacent(int from, int to) {
        LinkedList pointer = adjLists[from];
        Edge e = new Edge(from, to, 0);
        if (pointer.contains(e)) {
            return true;
        }
        return false;
    }

    /* Returns a list of all the vertices u such that the Edge (V, u)
       exists in the graph. */
    public List<Integer> neighbors(int v) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        LinkedList pointer = adjLists[v];
        for (int i = 0; i < pointer.size(); i++) {
            Edge e = (Edge) pointer.get(i);
            toReturn.add(e.to);
        }
        return toReturn;
    }
    /* Returns the number of incoming Edges for vertex V. */
    public int inDegree(int v) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i < adjLists.length; i++) {
            toReturn.addAll(incomingEdges(i, v));
        }
        return toReturn.size();
    }

    /* return a list of vertices v such that the edge (v, U) exists */
    public List<Integer> incomingEdges(int v, int u) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        LinkedList pointer = adjLists[v];
        for (int i = 0; i < pointer.size(); i++) {
            Edge e = (Edge) pointer.get(i);
            if (e.to == u) {
                toReturn.add(e.to);
            }
        }
        return toReturn;
    }

    /* Returns an Iterator that outputs the vertices of the graph in topological
       sorted order. */
    public Iterator<Integer> iterator() {
        return new TopologicalIterator();
    }

    /* A class that iterates through the vertices of this graph, starting with
       vertex START. If the iteration from START has no path from START to some
       vertex v, then the iteration will not include v. */
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;

        DFSIterator(int start) {
            fringe = new Stack<>();
            visited = new HashSet<>();
            fringe.push(start);
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            if (hasNext()) {
                int from = fringe.pop();
                LinkedList pointer = adjLists[from];
                ArrayList<Edge> sorted = sortedArray(pointer);

                for (int i = 0; i < sorted.size(); i++) {
                    Edge e = (Edge) sorted.get(i);
                    int toInsert = e.to;
                    if (!visited.contains(toInsert)) {
                        fringe.push(toInsert);
                        visited.add(toInsert);
                    }
                }
                visited.add(from);
                return from;
            }
            return null;
        }

        private ArrayList<Edge> sortedArray(LinkedList l) {
            ArrayList<Edge> toReturn = new ArrayList<>();
            for (int i = 0; i < l.size(); i++) {
                toReturn.add((Edge) l.get(i));
            }
            toReturn.sort((o1, o2) -> o2.to - o1.to);
            return toReturn;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /* Returns the collected result of performing a depth-first search on this
       graph's vertices starting from V. */
    public List<Integer> dfs(int v) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new DFSIterator(v);

        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    /* Returns true iff there exists a path from START to STOP. Assumes both
       START and STOP are in this graph. If START == STOP, returns true. */
    public boolean pathExists(int start, int stop) {
        return pathExistsHelper(start, stop, new ArrayList<Integer>());
    }

    public boolean pathExistsHelper(int start, int stop, ArrayList seen) {
        if (start == stop) {
            return true;
        } else if (seen.contains(start)) {
            return false;
        } else {
            LinkedList pointer = adjLists[start];
            seen.add(start);
            for (int i = 0; i < pointer.size(); i++) {
                Edge e = (Edge) pointer.get(i);
                if (e.to == stop) {
                    return true;
                }
            }
            boolean b = false;
            for (int i = 0; i < pointer.size(); i++) {
                Edge e = (Edge) pointer.get(i);
                b = b || pathExistsHelper(e.to, stop, seen);
            }
            return b;
        }
    }


    /* Returns the path from START to STOP. If no path exists, returns an empty
       List. If START == STOP, returns a List with START. */
    public List<Integer> path(int start, int stop) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        if (!pathExists(start, stop)) {
            return toReturn;
        } else if (start == stop) {
            toReturn.add(start);
            return toReturn;
        } else {
            ArrayList<Integer> largePath = new ArrayList<>();
            Iterator<Integer> iter = new DFSIterator(start);
            while (iter.hasNext() && !largePath.contains(stop)) {
                largePath.add(iter.next());
            }
            toReturn =  findPath(start, stop, largePath, new ArrayList<Integer>());
            return toReturn;
        }
    }

    private ArrayList<Integer> findPath(int start, int stop, ArrayList l, ArrayList seen) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        seen.add(start);
        toReturn.add(start);
        if (isAdjacent(start, stop)) {
            toReturn.add(stop);
            return toReturn;
        } else {
            for (int i = 0; i < l.size(); i++) {
                if (pathExists((Integer) l.get(i), stop) && !seen.contains(l.get(i))) {
                    seen.add(start);
                    toReturn.addAll(findPath((Integer) l.get(i), stop, l, seen));
                    break;
                }
            }
            return toReturn;
        }
    }

    public List<Integer> topologicalSort() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Iterator<Integer> iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;


        TopologicalIterator() {
            fringe = new Stack<Integer>();
        }

        public boolean hasNext() {
            return false;
        }

        public Integer next() {
            return 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private class Edge {

        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }

        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            return this.from == e.from && this.to == e.to;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    private void generateG1() {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(4, 3);
    }

    private void generateG2() {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(4, 3);
    }

    private void generateG3() {
        addUndirectedEdge(0, 2);
        addUndirectedEdge(0, 3);
        addUndirectedEdge(1, 4);
        addUndirectedEdge(1, 5);
        addUndirectedEdge(2, 3);
        addUndirectedEdge(2, 6);
        addUndirectedEdge(4, 5);
    }

    private void generateG4() {
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(4, 2);
    }

    private void generateG5() {
        addEdge(1, 3);
        addEdge(2, 5);
        addEdge(2, 4);
        addEdge(3, 4);
        addEdge(3, 7);
        addEdge(3, 9);
        addEdge(3, 6);
        addEdge(3, 1);
        addEdge(4, 2);
        addEdge(4, 8);
        addEdge(4, 7);
        addEdge(4, 9);
        addEdge(4, 6);
        addEdge(4, 3);
        addEdge(5, 8);
        addEdge(5, 7);
        addEdge(5, 2);
        addEdge(6, 3);
        addEdge(6, 4);
        addEdge(6, 7);
        addEdge(6, 9);
        addEdge(7, 4);
        addEdge(7, 5);
        addEdge(7, 8);
        addEdge(7, 10);
        addEdge(7, 9);
        addEdge(7, 6);
        addEdge(7, 3);
        addEdge(8, 5);
        addEdge(8, 7);
        addEdge(8, 4);
        addEdge(9, 3);
        addEdge(9, 4);
        addEdge(9, 7);
        addEdge(9, 6);
        addEdge(10, 7);
    }

    private void generateG6() {
        addUndirectedEdge(0, 1);
        addUndirectedEdge(0, 2);
        addUndirectedEdge(1, 3);
        addUndirectedEdge(2, 3);
        addUndirectedEdge(3, 4);
    }

    private void printDFS(int start) {
        System.out.println("DFS traversal starting at " + start);
        List<Integer> result = dfs(start);
        Iterator<Integer> iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
    }

    private void printPath(int start, int end) {
        System.out.println("Path from " + start + " to " + end);
        List<Integer> result = path(start, end);
        if (result.size() == 0) {
            System.out.println("No path from " + start + " to " + end);
            return;
        }
        Iterator<Integer> iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();
        System.out.println();
    }

    private void printTopologicalSort() {
        System.out.println("Topological sort");
        List<Integer> result = topologicalSort();
        Iterator<Integer> iter = result.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next() + " ");
        }
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(6);
        g1.generateG6();
//        g1.printDFS(1);
//        g1.printDFS(0);
//        g1.printDFS(2);
//        g1.printDFS(3);
//        g1.printDFS(4);
//        g1.printDFS(6);

        g1.printPath(0, 3);

//        Graph g2 = new Graph(5);
//        g2.generateG2();
//        g2.printTopologicalSort();
    }
}
