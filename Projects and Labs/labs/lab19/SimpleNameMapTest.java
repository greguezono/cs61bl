import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleNameMapTest {

    @Test
    public void constructorTest() {
        SimpleNameMap a = new SimpleNameMap();
        assertFalse(a.containsKey("Gregory"));
        a.put("Gregory", "Uezono");
        a.put("Giggs", "Ryan");
        assertTrue(a.containsKey("Gregory"));
        assertTrue(a.containsKey("Giggs"));
        assertEquals(a.get("Gregory"), "Uezono");
        assertEquals(a.get("Giggs"), "Ryan");
        a.remove("Gregory");
        assertFalse(a.containsKey("Gregory"));
        assertEquals(a.get("Gregory"), null);
    }
    @Test
    public void test2() {
        SimpleNameMap a = new SimpleNameMap();
        assertFalse(a.containsKey("Gregory"));
        a.put("Gregory", "Uezono");
        assertTrue(a.containsKey("Gregory"));
        a.put("Xing", "Voong");
        assertEquals(a.size(), 2);
        a.put("George", "McGuigan");
        assertEquals(a.size(), 3);
        a.put("Hi", "Greg");
        a.put("What", "The");
        a.put("F", "U");
        a.put("Stephen", "Jimmy");
        assertEquals(a.size(), 7);
        a.put("Nate", "Nasty");
        assertEquals(a.size(), 8);
        System.out.println(a.loadFactor());
    }

}
