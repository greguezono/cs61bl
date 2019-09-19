import java.util.LinkedList;
import java.util.Iterator;

public class BST<T> {

    BSTNode<T> root;

    public BST(LinkedList<T> list) {
        root = sortedIterToTree(list.iterator(), list.size());
    }

    /* Returns the root node of a BST (Binary Search Tree) built from the given
       iterator ITER  of N items. ITER will output the items in sorted order,
       and ITER will contain objects that will be the item of each BSTNode. */
    private BSTNode<T> sortedIterToTree(Iterator<T> iter, int N) {
        return sortedIterHelper(iter, 0, N);
    }

    private BSTNode<T> sortedIterHelper(Iterator<T> iter, int start, int end) {
        if (start > end || !iter.hasNext()) {
            return null;
        }
        int mid = (start + end) / 2;
        BSTNode<T> left = sortedIterHelper(iter, start, mid - 1);
        BSTNode<T> r = new BSTNode<>(iter.next());
        BSTNode<T> right = sortedIterHelper(iter, mid + 1, end);
        r.left = left;
        r.right = right;
        return r;
    }

    /* Prints the tree represented by ROOT. */
    private void print() {
        print(root, 0);
    }

    private void print(BSTNode<T> node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item);
        print(node.left, d + 1);
        print(node.right, d + 1);
    }

    class BSTNode<T> {
        T item;
        BSTNode<T> left;
        BSTNode<T> right;

        BSTNode(T item) {
            this.item = item;
        }
    }
}
