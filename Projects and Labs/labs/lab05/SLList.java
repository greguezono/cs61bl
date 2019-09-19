import java.util.Objects;

/**
  * An SLList is a list of integers, which hides the terrible truth of the
  * nakedness within.
  */
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        @Override
        public String toString() {
            return "IntNode{" + "item=" + item + ", next=" + next + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            IntNode intNode = (IntNode) o;
            return item == intNode.item && Objects.equals(next, intNode.next);
        }

        @Override
        public int hashCode() {

            return Objects.hash(item, next);
        }

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private int size;

    @Override
    public String toString() {
        return "SLList{" + "sentinel=" + sentinel + ", size=" + size + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SLList slList = (SLList) o;
        return size == slList.size && Objects.equals(sentinel, slList.sentinel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sentinel, size);
    }

    /** Creates an empty SLList. */
    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    /** Returns an SLList consisting of the given values. */
    public static SLList of(int... values) {
        SLList list = new SLList();
        for (int i = values.length - 1; i >= 0; i -= 1) {
            list.addFirst(values[i]);
        }
        return list;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    /** Returns the first item in the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Return the value at the given index. */
    public int get(int index) {
        IntNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    /** Adds x to the list at the specified index. */
    public void add(int index, int x) {
        if (index == 0 || sentinel.next == null) {
            sentinel.next = new IntNode(x, sentinel.next);
        } else if (index > size) {
            IntNode y = sentinel.next;
            while (y.next != null && index > 0) {
                y = y.next;
            }
            y.next = new IntNode(x, null);
        } else {
            IntNode y = sentinel.next;
            while (y != null && index > 0) {
                y = y.next;
                index--;
            }
            IntNode temp = new IntNode(y.item, y.next);
            y.item = x;
            y.next = temp;
        }
        size += 1;
    }

    /** Returns the reverse of this list. This method is destructive. */
    public void reverse() {
        if (size == 0 || size == 1) {
            return;
        }

        int[] l = new int[size];
        IntNode p1 = sentinel.next;
        for (int i = 0; i < size; i++) {
            l[i] = p1.item;
            p1 = p1.next;
        }
        IntNode p2 = sentinel.next;
        for (int i = l.length - 1; i >= 0; i--) {
            p2.item = l[i];
            p2 = p2.next;
        }
    }
}
