
import org.junit.Test;
import java.util.ArrayList;

public class GraphTesting {

    @Test
    public void basicTest() {
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1, 10);
        g1.addEdge(0, 4, 100);
        g1.addEdge(0, 3, 30);
        g1.addEdge(1, 2, 50);
        g1.addEdge(2, 4, 10);
        g1.addEdge(3, 2, 20);
        g1.addEdge(3, 4, 60);
        ArrayList<Integer> a = (ArrayList) g1.shortestPath(0, 4);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
