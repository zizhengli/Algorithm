package test;

import java.util.*;

/**
 * Created by zizhengli on 9/17/18.
 */
public class HashTableTest {

    static int longestSubarrayWithDistinctEntries(List<Character> A) {
        if(A == null || A.size() == 0) {
            return -1;
        }
        Map<Character, Integer> mostRecentOccurenceIdx = new HashMap<>();
        int left = 0;
        int right = 0;
        int result = 0;
        while(right < A.size()) {
            Integer idx = mostRecentOccurenceIdx.get(A.get(right));
            if(idx != null) {
                result = Math.max(result, right - left);
                left = idx + 1;
            }
            mostRecentOccurenceIdx.put(A.get(right), right);
            right++;
        }
        result = Math.max(result, A.size() - left);
        return result;
    }

    public static void main(String[] args) {

        List<Character> chars = new ArrayList<>(Arrays.asList('f', 's', 'f', 's', 'f', 's', 'i', 'w', 'w', 'e'));
        System.out.println(longestSubarrayWithDistinctEntries(chars));
    }

}
