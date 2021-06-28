package sorting;

//import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a program which takes as input two sorted arrays, and returns a new array
 containing elements that are present in both of the input arrays. The input arrays
 may have duplicate entries, but the returned array should be free of duplicates. For
 example, the input is (2,3,3,5,5,6, 7, 7,8,12} and (5,5,6,8,8,9,10,10), your output
 should be (5,6,8)
 */
public class IntersectSortedArray {

    public static List<Integer> intersectTwoSortedArrays(List<Integer> list1, List<Integer> list2) {
        if(list1 == null || list1.size() == 0) {
            return list2;
        } else if(list2 == null || list2.size() == 0) {
            return list1;
        }
        int index1 = 0;
        int index2 = 0;
        List<Integer> result = new ArrayList<>();

        while(index1 < list1.size() && index2 < list2.size()) {
            if(list1.get(index1) == list2.get(index2)) {
                if(index1 > 0 && list1.get(index1) != list1.get(index1 - 1)) {
                    result.add(list1.get(index1));
                }
                index1++;
                index2++;
            } else if(list1.get(index1) < list2.get(index2)) {
                index1++;
            } else {
                index2++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> result = IntersectSortedArray.intersectTwoSortedArrays(Arrays.asList(2,3,3,5,5,6,7,7,8,12),
                                                                            Arrays.asList(5,5,6,8,8,9,10,10));
        //Assert.assertArrayEquals(result.toArray(new Integer[0]), new Integer[] {5,6,8});
    }
}
