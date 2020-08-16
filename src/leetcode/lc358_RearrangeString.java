package leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class lc358_RearrangeString {

    public String rearrangeString(String s, int k) {
        /**
         利用贪心思想 检查每个字符的个数 在PQ中按照个数排序 从个数最多的开始加
         比较tricky的地方是 用一个FIFO queue来控制等待被重新加入队列的字符
         这里用queue的目地是为了记录处理过的字符 而且他们已经准备好再被push到pq中
         */
        int[] map = new int[26];
        for(int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        PriorityQueue<Element> pq = new PriorityQueue(k, new Comparator<Element>() {
            public int compare(Element e1, Element e2) {
                if(e1.freq != e2.freq) {
                    return e2.freq - e1.freq;
                } else {
                    return e1.c - e2.c;
                }
            }
        });

        for(int i = 0; i < map.length; i++) {
            if(map[i] > 0) {
                pq.add(new Element((char)(i + 'a'), map[i]));
            }
        }
        Queue<Element> queue = new LinkedList();
        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            Element e = pq.poll();
            sb.append(e.c);
            e.freq = e.freq - 1;
            queue.add(e);
            if(queue.size() == k) {
                Element eInQueue = queue.poll();
                if(eInQueue.freq > 0) {
                    pq.add(eInQueue);
                }
            }
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }

    private class Element {
        char c;
        int freq;

        Element(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }
}
