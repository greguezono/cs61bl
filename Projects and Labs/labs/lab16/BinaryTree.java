import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {

    protected TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode t) {
        root = t;
    }

    /* Constructs a binary tree based on a given preorder traversal PRE and an
       inorder traversal IN. */
    public BinaryTree(ArrayList<T> pre,  ArrayList<T> in) {
        root = listHelper(pre, in);
    }

    private TreeNode listHelper(ArrayList<T> pre,  ArrayList<T> in) {
        return listHelperHelper(pre, in, 0);
    }

    private TreeNode listHelperHelper(ArrayList<T> pre,  ArrayList<T> in, int indexOfPre) {
        if (indexOfPre >= pre.size() || in == null) {
            return null;
        }
        T r = pre.get(indexOfPre);
        ArrayList<T> left = getArray(0, in, r);
        int indexRight = in.indexOf(r) + 1;
        ArrayList<T> right = getArray(indexRight, in, r);
        TreeNode leftTree = listHelperHelper(pre, left, indexOfPre + 1);
        TreeNode rightTree = listHelperHelper(pre, right, indexOfPre + 2);
        return new TreeNode(r, leftTree, rightTree);
    }

    private ArrayList<T> getArray(int index, ArrayList<T> arr, T r) {
        ArrayList<T> array = new ArrayList<>();
        while (index < arr.size() && arr.get(index) != r) {
            T a = arr.get(index);
            array.add(a);
            index += 1;
        }
        if (array.size() == 0) {
            return null;
        }
        return array;
    }


    /* Print the values in the tree in preorder. */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    /* Print the values in the tree in inorder. */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    /* Prints the BinaryTree in preorder or in inorder. Used for testing. */
    protected static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    protected class TreeNode {

        T item;
        TreeNode left;
        TreeNode right;
        int size = 0;

        public TreeNode(T item) {
            this.item = item; left = right = null;
        }

        public TreeNode(T item, TreeNode left, TreeNode right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        /* Prints the nodes of the BinaryTree in preorder. Used for testing. */
        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        /* Prints the nodes of the BinaryTree in inorder. Used for testing. */
        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }

        public boolean contains(T key) {
            return containsHelper(key, this);
        }

        private boolean containsHelper(T key, TreeNode t) {
            if (t == null) {
                return false;
            } else if (t.item == key) {
                return true;
            } else if (t.item.compareTo(key) < 0) {
                return containsHelper(key, t.right);
            } else {
                return containsHelper(key, t.left);
            }
        }

        public void addBinSearch(T key) {
            if (item.compareTo(key) > 0) {
                if (left == null) {
                    left = new TreeNode(key);
                } else {
                    left.addBinSearch(key);
                }
            } else if (item.compareTo(key) < 0) {
                if (right == null) {
                    right = new TreeNode(key);
                } else {
                    right.addBinSearch(key);
                }
            }
        }
    }
}
