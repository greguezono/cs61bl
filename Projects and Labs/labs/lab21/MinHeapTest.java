import org.junit.Test;
import static org.junit.Assert.*;

public class MinHeapTest {
    @Test
    public void insertTest() {
        MinHeap c = new MinHeap();
        c.insert(50);
        c.insert(60);
        c.insert(70);
        System.out.println(c);
        c.insert(1);
        System.out.println(c);
        c.insert(2);
        c.insert(5);
        System.out.println(c);
        c.removeMin();
        System.out.println(c);

    }
    @Test
    public void deleteTEst() {
        MinHeap a = new MinHeap();
        a.insert(1);
        a.insert(2);
        a.insert(3);
        MinHeap b = new MinHeap();
        b.insert(5);
        b.insert(4);
        b.insert(10);
        b.insert(1);
        assertEquals(b.removeMin(), 1);
        assertEquals(b.peek(), 4);
        assertEquals(b.removeMin(), 4);
        assertEquals(b.removeMin(), 5);
        MinHeap c = new MinHeap();
        c.insert(50);
        c.insert(60);
        c.insert(70);
        c.insert(1);
        System.out.println(c);
    }

    @Test
    public void minHeapPQTest() {
        MinHeapPQ<String> a = new MinHeapPQ<>();
        a.insert("hi", 100);
        a.insert("there", 50);
        a.insert("why", 75);
        assertEquals(a.peek(), "there");
        a.changePriority("hi", 10);
        assertEquals(a.peek(), "hi");
        a.changePriority("hi", 150);
        a.changePriority("there", 125);
        assertEquals(a.peek(), "why");
    }
}
