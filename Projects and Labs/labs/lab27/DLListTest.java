import org.junit.Test;

public class DLListTest {
    @Test
    public void inserttionSort() {
        DLList<Integer> a = new DLList<>();
        a.addLast(6);
        a.addLast(5);
        a.addLast(3);
        a.addLast(9);
        a.addLast(1);
        a.addLast(8);
        a.addLast(3);
        a.addLast(0);
        a.addLast(2);
        a.addLast(9);
        a.addLast(7);
        a.addLast(4);
        a.addLast(0);
        System.out.println(a);
        DLList<Integer> b = a.insertionSort();
        System.out.println(b);
    }

    @Test
    public void mergeSort() {
        DLList<Integer> a = new DLList<>();
        // 0 2 3 1 5 6
        a.addFirst(6);
        a.addFirst(5);
        a.addFirst(1);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(0);
        DLList<Integer> b = a.mergeSort();
        System.out.println(b);
        System.out.println(a);
    }

    @Test
    public void quickSort() {
        DLList<Integer> a = new DLList<>();
        a.addLast(6);
        a.addLast(5);
        a.addLast(3);
        a.addLast(9);
        a.addLast(1);
        a.addLast(8);
        a.addLast(3);
        a.addLast(0);
        a.addLast(2);
        a.addLast(9);
        a.addLast(7);
        a.addLast(4);
        a.addLast(0);
        DLList<Integer> b = a.quicksort();
        System.out.println(b);
        System.out.println(a);
    }
}
