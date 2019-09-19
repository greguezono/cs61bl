import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CodingChallengesTest {
    @Test
    public void missingNumberTest() {
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{0, 2, 3, 4};
        int[] c = new int[]{0, 1, 2, 3};
        int actual1 = CodingChallenges.missingNumber(a);
        int actual2 = CodingChallenges.missingNumber(b);
        int actual3 = CodingChallenges.missingNumber(c);
        assertEquals(actual1, 0);
        assertEquals(actual2, 1);
        assertEquals(actual3, -1);
    }
    @Test
    public void sumToTest() {
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{4, 2, 6, 10};
        int[] c = new int[]{2, 2, 30, 100};
        boolean actual1 = CodingChallenges.sumTo(a, 5);
        boolean actual2 = CodingChallenges.sumTo(a, 7);
        boolean actual3 = CodingChallenges.sumTo(b, 15);
        boolean actual4 = CodingChallenges.sumTo(b, 12);
        boolean actual5 = CodingChallenges.sumTo(c, 4);
        assertEquals(actual1, true);
        assertEquals(actual2, true);
        assertEquals(actual3, false);
        assertEquals(actual4, true);
        assertEquals(actual5, true);

    }
    @Test
    public void stringTest() {
        Map<Character, Integer> st1 = new HashMap<>();
        CodingChallenges.addToMap(st1, "hello");
        st1.values();
    }
    @Test
    public void isPermutationTest() {
        String s1 = "hello";
        String s2 = "loleh";
        String s3 = "lo";
        String s4 = "hellos";
        String s5 = "what";
        boolean actual1 = CodingChallenges.isPermutation(s1, s2);
        boolean actual2 = CodingChallenges.isPermutation(s1, s2);
        boolean actual3 = CodingChallenges.isPermutation(s3, s2);
        boolean actual4 = CodingChallenges.isPermutation(s4, s1);
        boolean actual5 = CodingChallenges.isPermutation(s1, s3);
        boolean actual6 = CodingChallenges.isPermutation(s5, s4);
        assertEquals(actual1,  true);
        assertEquals(actual2, true);
        assertEquals(actual3, false);
        assertEquals(actual4, false);
        assertEquals(actual5, false);
        assertEquals(actual6, false);
    }
}
