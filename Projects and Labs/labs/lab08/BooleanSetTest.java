import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanSetTest {

    @Test
    public void testConstructor() {
        BooleanSet aSet = new BooleanSet(100);
        SimpleSet anotherSet = aSet;
        SimpleCollection anotherCollection = aSet;
    }

    @Test
    public void testBasics() {
        BooleanSet aSet = new BooleanSet(100);
        assertEquals(0, aSet.size());
        for (int i = 0; i < 100; i += 2) {
            aSet.add(i);
            assertTrue(aSet.contains(i));
        }
        assertEquals(50, aSet.size());

        for (int i = 0; i < 100; i += 2) {
            aSet.remove(i);
            assertFalse(aSet.contains(i));
        }
        assertTrue(aSet.isEmpty());
        assertEquals(0, aSet.size());
    }

    @Test
    public void testtoIntArray() {
        BooleanSet aSet = new BooleanSet(18);
        aSet.add(0);
        aSet.add(1);
        aSet.add(6);
        aSet.add(10);
        aSet.add(11);
        aSet.add(14);
        aSet.add(17);
        int[] actual = aSet.toIntArray();
        int[] expected = new int[]{0, 1, 6, 10, 11, 14, 17};
        assertArrayEquals(actual, expected);
    }
}
