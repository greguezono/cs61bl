import java.util.ArrayList;
import java.util.Iterator;


/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> implements Iterable<E> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        return 2 * index;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        return 2 * index + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        if (index == 1) {
            return -1;
        }
        return index / 2;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. */
    private int min(int index1, int index2) {
        E item1 = contents.get(index1);
        E item2 = contents.get(index2);
        if (item1 == null) {
            return index2;
        } else if (item2 == null) {
            return index1;
        } else {
            if (item1.compareTo(item2) < 0) {
                return index1;
            } else {
                return index2;
            }
        }
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E peek() {
        return contents.get(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        int parent = getParentOf(index);
        if (parent < 0) {
            return;
        }
        int min = min(index, parent);
        if (min == index) {
            swap(parent, min);
            bubbleUp(parent);
        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        int leftIndex = getLeftOf(index);
        int rightIndex = getRightOf(index);
        E parent = getElement(index);
        E left = getElement(leftIndex);
        E right = getElement(rightIndex);
        if (left == null && right == null) {
            return;
        }
        if (right == null) {
            if (parent.compareTo(left) > 0) {
                swap(index, leftIndex);
                bubbleDown(index);
            } else {
                return;
            }
        } else if (left == null) {
            if (parent.compareTo(right) > 0) {
                swap(index, rightIndex);
                bubbleDown(index);
            }
        } else if (parent.compareTo(left) < 0 && parent.compareTo(right) < 0
                || parent.compareTo(left) == 0 && parent.compareTo((right)) == 0) {
            return;
        } else {
            if (parent.compareTo(left) > 0 && parent.compareTo(right) > 0) {
                int min = min(leftIndex, rightIndex);
                swap(index, min);
                bubbleDown(min);
            } else if (parent.compareTo(left) > 0) {
                swap(index, leftIndex);
                bubbleDown(leftIndex);
            } else if (parent.compareTo(right) > 0) {
                swap(index, rightIndex);
                bubbleDown(rightIndex);
            }
        }
    }

    /* Inserts element into the MinHeap. */
    public void insert(E element) {
        setElement(size + 1, element);
        bubbleUp(size + 1);
        size += 1;

    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        return size;
    }

    /* Returns the smallest element. */
    public E removeMin() {
        swap(1, size);
        E toReturn = getElement(size);
        setElement(size, null);
        size -= 1;
        bubbleDown(1);
        return toReturn;
    }

    /* Updates the position of ELEMENT inside the MinHeap, which may have been
       mutated since the inital insert. If a copy of ELEMENT does not exist in
       the MinHeap, do nothing.*/
    public void update(E element) {
        if (element == null) {
            return;
        }
        int index = getIndexofElement(element);
        bubbleUp(index);
        bubbleDown(index);
    }

    private int getIndexofElement(E element) {
        for (int i = 0; i < contents.size(); i++) {
            if (element.equals(contents.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return contents.iterator();
    }
}
