public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given BTree (2-3-4) TREE. */
    public RedBlackTree(BTree<T> tree) {
        Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3-4 tree rooted at
       given node R, and returns the root node. */
    public RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null || r.getItemCount() == 0) {
            return null;
        } else if (r.getItemCount() == 1) {
            T item = r.getItemAt(0);
            RBTreeNode<T> left = buildRedBlackTree(r.getChildAt(0));
            RBTreeNode<T> right = buildRedBlackTree(r.getChildAt(1));
            return new RBTreeNode<T>(true, item, left, right);
        } else if (r.getItemCount() == 2) {
            T parent = r.getItemAt(0);
            T child = r.getItemAt(1);
            RBTreeNode<T> left = buildRedBlackTree(r.getChildAt(0));
            RBTreeNode<T> rightLeft = buildRedBlackTree(r.getChildAt(1));
            RBTreeNode<T> rightRight = buildRedBlackTree(r.getChildAt(2));
            RBTreeNode<T> right = new RBTreeNode<>(false, child, rightLeft, rightRight);
            return new RBTreeNode<>(true, parent, left, right);
        } else {
            T parent = r.getItemAt(1);
            T leftItem = r.getItemAt(0);
            T rightItem = r.getItemAt(2);
            RBTreeNode<T> leftLeft = buildRedBlackTree(r.getChildAt(0));
            RBTreeNode<T> leftRight = buildRedBlackTree(r.getChildAt(1));
            RBTreeNode<T> left = new RBTreeNode<>(false, leftItem, leftLeft, leftRight);
            RBTreeNode<T> rightLeft = buildRedBlackTree(r.getChildAt(2));
            RBTreeNode<T> rightRight = buildRedBlackTree(r.getChildAt(3));
            RBTreeNode<T> right = new RBTreeNode<>(false, rightItem, rightLeft, rightRight);
            return new RBTreeNode<T>(true, parent, left, right);
        }
    }


    /* Flips the color of NODE and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /* Rotates the given node NODE to the right. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        return rotateHelperRight(node, node.left, node.right);
    }

    RBTreeNode<T> rotateHelperRight(RBTreeNode<T> node, RBTreeNode<T> leftSide,
                                    RBTreeNode<T> sideToCopy) {
        RBTreeNode<T> temp = leftSide.right;
        leftSide.right = node;
        leftSide.right.left = temp;
        leftSide.isBlack = node.isBlack;
        node.isBlack = false;
        return leftSide;
    }

    /* Rotates the given node NODE to the left. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        return rotateHelperLeft(node, node.right, node.left);
    }

    RBTreeNode<T> rotateHelperLeft(RBTreeNode<T> node, RBTreeNode<T> rightSide,
                                   RBTreeNode<T> sideToCopy) {
        RBTreeNode<T> temp = rightSide.left;
        rightSide.left = node;
        rightSide.left.right = temp;
        rightSide.isBlack = node.isBlack;
        node.isBlack = false;
        return rightSide;
    }

    /* Insert ITEM into the red black tree, rotating
       it accordingly afterwards. */
    void insert(T item) {
        insertHelper(item, root);
    }

    void insertHelper(T item, RBTreeNode<T> r) {
        if (r.left == null && r.right == null) {
            if (r.item.compareTo(item) > 0) {
                r.left = new RBTreeNode<>(false, item);
            } else {
                r.right = new RBTreeNode<>(false, item);
                RBTreeNode<T> p = parent(r);
                restoreProperties(r, p);
            }
        } else {
            RBTreeNode<T> current = null;
            RBTreeNode<T> pointer = r;
            boolean left = false;
            while (pointer != null) {
                if (pointer.item.compareTo(item) < 0) {
                    current = pointer;
                    pointer = pointer.right;
                    left = false;
                } else if (pointer.item.compareTo(item) > 0) {
                    current = pointer;
                    pointer = pointer.left;
                    left = true;
                } else {
                    pointer = null;
                }
            }
            if (left) {
                current.left = new RBTreeNode<>(false, item);
            } else {
                current.right = new RBTreeNode<>(false, item);
            }
            restoreProperties(current, parent(pointer));
        }
    }
    public void restoreProperties(RBTreeNode<T> current, RBTreeNode<T> parent) {
        if (current == null) {
            return;
        }
        if (current.left == null) {
            if (parent.equals(root)) {
                root = rotateLeft(root);
            } else if (childIsLeft(current)) {
                parent.left = rotateLeft(parent.left);
            } else {
                parent.right = rotateLeft(parent.right);
            }
        }
    }

    public boolean childIsLeft(RBTreeNode<T> p) {
        return childIsLeftHelper(root, p);
    }

    public boolean childIsLeftHelper(RBTreeNode<T> currentRoot, RBTreeNode<T> p) {
        if (currentRoot == null) {
            return false;
        } else {
            if (currentRoot.left == p) {
                return true;
            } else if (currentRoot.right == p) {
                return false;
            } else {
                if (currentRoot.item.compareTo(p.item) < 0) {
                    return childIsLeftHelper(currentRoot.right, p);
                } else {
                    return childIsLeftHelper(currentRoot.left, p);
                }
            }
        }
    }

    public RBTreeNode<T> parent(RBTreeNode<T> p) {
        return parentHelper(root, p);
    }

    private RBTreeNode<T> parentHelper(RBTreeNode<T> currentRoot, RBTreeNode<T> p) {
        if (currentRoot == null) {
            return null;
        } else if (p.equals(root)) {
            return root;
        } else {
            if (currentRoot.left == p || currentRoot.right == p) {
                return currentRoot;
            } else {
                if (currentRoot.item.compareTo(p.item) < 0) {
                    return parentHelper(currentRoot.right, p);
                } else {
                    return parentHelper(currentRoot.left, p);
                }
            }
        }
    }

    /* Returns whether the given node NODE is red. Null nodes (children of leaf
       nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }

    }

}
