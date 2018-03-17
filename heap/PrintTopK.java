package src.heap;

import src.utils.ArrayUtils;

import java.util.HashMap;

/**
 * 给定String类型的数组strArr,再给定整数k,请严格按照排名顺序 打印出现次数前k名的字符串。
 * follow up :
 * 设计并实现TopKRecord结构,可以不断地向其中加入字符串,并且可以随时打印加入次数最多前k个字符串。
 */
public class PrintTopK {

    static class Node {
        String s;
        int freq;
        Node(String s, int freq) {
            this.s = s;
            this.freq = freq;
        }
    }
    public static void printTopK(String[] strings, int k) {

        HashMap<String, Integer> frequencyMap = new HashMap();
        for(String s : strings) {
            if(frequencyMap.containsKey(s)) {
                frequencyMap.put(s, frequencyMap.get(s) + 1);
            } else {
                frequencyMap.put(s, 1);
            }
        }

        Node[] topK = new Node[k];
        int index = 0;
        for(String s : frequencyMap.keySet()) {
            Node node = new Node(s, frequencyMap.get(s));
            System.out.println(s + " " + frequencyMap.get(s));
            if(index != k) {
                topK[index++] = node;
            } else {
                minHeapify(topK, node);
            }
        }
        printHeap(topK);
    }

    private static void printHeap(Node[] topK) {
        for(Node node : topK) {
            System.out.print(node.s + ":" + node.freq + ", ");
        }
    }

    private static void minHeapify(Node[] topK, Node s) {

        if(topK[0].freq < s.freq) {
            topK[0] = s;
            int index = 0;
            while(index < topK.length) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;

                if(left < topK.length && topK[index].freq > topK[left].freq ) {
                    swap(topK, index, left);
                } else if(right < topK.length && topK[index].freq > topK[right].freq ) {
                    swap(topK, index, right);
                } else {
                    break;
                }
                index++;
            }
        }
    }

    private static void swap(Node[] topK, int a, int b) {
        Node tmp = topK[a];
        topK[a] = topK[b];
        topK[b] = tmp;
    }

    public static void main(String[] args) {

        String[] test = ArrayUtils.generateRandomArray(50, 10);
        PrintTopK.printTopK(test, 4);
    }
}
