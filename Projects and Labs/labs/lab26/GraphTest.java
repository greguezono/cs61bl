import org.junit.Test;

public class GraphTest {
    @Test
    public void genericTest() {
        Graph g = Graph.loadFromText("inputs/graphCustomTest.in");
        Graph mst = g.kruskals();
        for (Edge e: mst.getAllEdges()) {
            System.out.println(e);
        }
    }
}
