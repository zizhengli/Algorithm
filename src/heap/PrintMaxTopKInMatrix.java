package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 有N个长度不一样的数组,所有数组中的元素都是从小到大有序 排列的,请从大到小打印这N个数组整体的前K个数
 *
 * Similar to lc23
 */
public class PrintMaxTopKInMatrix {

    // Class 2 problem 9 todo

    static void printTopK(List<List<Integer>> lists, int k) {
        if(lists == null || lists.size() == 0 || k <= 0) {
            return;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o1 - o2);

        for(List<Integer> list : lists) {
            for(Integer i : list) {
                if(queue.size() >= k) {
                    if(i < queue.peek()) {
                        continue;
                    }
                    queue.poll();
                }
                queue.add(i);
            }
        }

        Integer[] result = new Integer[k];
        for(int i = queue.size() - 1; i >= 0; i--) {
            result[i] = queue.poll();
        }

        for(Integer i : result) {
            System.out.print(i + " ");
        }
    }


    public static void main(String[] args) {

        List<List<Integer>> lists = new ArrayList();
        lists.add(Arrays.asList(1, 2, 4, 10, 20, 21));
        lists.add(Arrays.asList(1, 3, 3, 5, 6, 50));
        lists.add(Arrays.asList(11, 12, 13, 15, 15, 23));

        PrintMaxTopKInMatrix.printTopK(lists, 5);
    }
}
