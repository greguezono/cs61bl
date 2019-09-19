import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class CodingChallenges {

    /**
     * Return the missing number from an array of length N - 1 containing all
     * the values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        // TODO
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < values.length; i++) {
            set.add(values[i]);
        }

        for (int i = 0; i < values.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    /** Returns true if and only if two integers in the array sum up to n. */
    public static boolean sumTo(int[] values, int n) {
        // TODO
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i] <= n) {
                list.add(values[i]);
            }
        }
        for (int i = 0; i < values.length; i++) {
            if (list.contains(n - list.get(i)) && list.indexOf(n - list.get(i)) != i) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        Map<Character, Integer> st1 = new HashMap<>();
        Map<Character, Integer> st2 = new HashMap<>();
        addToMap(st1, s1);
        addToMap(st2, s2);
        if (st2.size() < st1.size()) {
            return false;
        } else {
            for (char key : st1.keySet()) {
                if (!st2.containsKey(key)) {
                    return false;
                } else {
                    int x = (Integer) st1.get(key);
                    int y = (Integer) st2.get(key);
                    if (x != y) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void addToMap(Map st, String s1) {
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (st.containsKey(c)) {
                int x = (Integer) st.get(c);
                st.put(c, x + 1);
            } else {
                st.put(c, 1);
            }
        }
    }

}
