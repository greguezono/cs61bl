import org.junit.Test;

public class CountingSortsTests {
    @Test
    public void countingTest() {
        // 5 8 2 2 1 9 0 3
        int[] arr = new int[8];
        arr[0] = 356;
        arr[1] = 112;
        arr[2] = 904;
        arr[3] = 294;
        arr[4] = 209;
        arr[5] = 820;
        arr[6] = 394;
        arr[7] = 810;
        // 0 1 2 2 3 5 8 9
//        DistributionSorts.countingSort(arr);
        DistributionSorts.lsdRadixSort(arr);
        for (int i: arr) {
            System.out.print(i + " ");
        }
    }
}
