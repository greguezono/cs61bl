import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class BinaryTreeTest {
    @Test
    public void treeFormatTest() {
        BinarySearchTree<String> x = new BinarySearchTree<String>();
        x.add("C");
        x.add("A");
        x.add("E");
        x.add("B");
        x.add("D");
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(outContent));
        BinaryTree.print(x, "x");
        System.setOut(oldOut);
        assertEquals("x in preorder\nC A B E D \nx in inorder\nA B C D E \n\n".trim(),
                     outContent.toString().trim());
    }

    @Test
    public void testContains() {
        BinarySearchTree<String> x = new BinarySearchTree<String>();
        x.add("D");
        x.add("B");
        x.add("A");
        x.add("C");
        x.add("F");
        x.add("E");
        x.add("G");
        assertEquals(x.contains("G"), true);
        assertEquals(x.contains("F"), true);
        assertEquals(x.contains("A"), true);
        assertEquals(x.contains("D"), true);
        assertEquals(x.contains("B"), true);
        assertEquals(x.contains("X"), false);
        BinarySearchTree<String> y = new BinarySearchTree<>();
        assertEquals(y.contains("A"), false);
        x.printPreorder();
        x.printInorder();
    }

    @Test
    public void testPreinOrder() {
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> b = new ArrayList<>();
        a.add("A");
        a.add("B");
        a.add("C");
        a.add("D");
        a.add("E");
        a.add("F");
        b.add("B");
        b.add("A");
        b.add("E");
        b.add("D");
        b.add("F");
        b.add("C");
        BinaryTree<String> binTree = new BinaryTree<>(a, b);
        binTree.printInorder();
        binTree.printPreorder();
    }
}
