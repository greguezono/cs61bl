/**
 * A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
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
        for (int i = 1; i < values.length; i++) {
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
        if (position > size()) {
          return 0;
        }
        IntList p = this;
        while (position > 0) {
          p = p.rest;
          position--;
        }
        return p.first;
    }

    /** Returns the string representation of the list. */
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
    public static void main(String[] args) {
      IntList L = new IntList(15, null);
      L = new IntList(10, L);
      L = new IntList(5, L);
      IntList o = new IntList(20, null);
      o = new IntList(10, o);
      o = new IntList(5, o);
      System.out.println(L);
      System.out.println(o);
      System.out.println(L.equals(o));
    }
}
