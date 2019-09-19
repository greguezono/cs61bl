/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 *
 * This is a dummy implementation to allow IntListTest to compile. Replace this
 * file with your own IntList class.
 */
public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Returns an IntList consisting of the given values. */
    public static IntList of(int... values) {
        if (values.length == 0) {
            return null;
        }
        IntList p = new IntList(values[0], null);
        IntList front = p;
        for (int i = 1; i < values.length; i += 1) {
            p.rest = new IntList(values[i], null);
            p = p.rest;
        }
        return front;
    }

    /** Returns the size of the list. */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + rest.size();
    }

    /** Returns [position]th value in this list. */
    public int get(int position) {
        if (position == 0) {
            return first;
        } else {
            return rest.get(position - 1);
        }
    }
    public String toString() {
        String s = "";
        for (int i = 0; i < size(); i++) {
            s = s + this.get(i) + " ";
        }
        return s;
    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        IntList other = (IntList) o;
        IntList p = this;
        if (p.size() != other.size()) {
            return false;
        }
        while (p.rest != null && other.rest != null) {
            if (p.first != other.first) {
                return false;
            }
            p = p.rest;
            other = other.rest;
        }
        return p.first == other.first;
    }

    public void add(int value) {

        if (rest == null) {
            rest = new IntList(value, null);

        } else {
            this.rest.add(value);
        }
    }

    public int smallest() {
        IntList p = this;
        int s = p.first;
        while (p.rest != null) {
            if (p.rest.first < s) {
                s = p.rest.first;
            }
            p = p.rest;
        }

        return s;
    }

    public int squaredSum() {
        int sum = 0;
        IntList p = this;
        while (p.rest != null) {
            sum += p.first * p.first;
            p = p.rest;
        }
        sum += p.first * p.first;
        return sum;
    }

    public static void dSquareList(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    public static IntList catenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        IntList a = new IntList(A.first, null);
        IntList p = a;
        A = A.rest;
        while (A != null) {
            p.rest = new IntList(A.first, null);
            A = A.rest;
            p = p.rest;
        }
        while (B != null) {
            p.rest = new IntList(B.first, null);
            B = B.rest;
            p = p.rest;
        }
        return a;
    }

    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            A = B;
            return A;
        }
        if (B == null) {
            return A;
        }
        IntList p = A;
        while (A.rest != null) {
            A = A.rest;
        }
        while (B.rest != null) {
            A.rest = new IntList(B.first, null);
            A = A.rest;
            B = B.rest;
        }
        A.rest = new IntList(B.first, null);
        return p;
    }
}
