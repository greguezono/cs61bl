import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;


public class Tests {

    @Test
    public void constructorTest() {
        LinkedList<Integer> x = new LinkedList<>();
        x.add(1);
        BST<Integer> z = new BST<Integer>(x);
        System.out.println(z);
    }
}
