package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class lc1514_PathWithMaximumProbability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        if(edges == null || edges.length == 0) {
            return 0.0;
        }

        Map<Integer, List<Node>> graph = new HashMap();
        for(int i = 0; i < edges.length; i++) {
            if(!graph.containsKey(edges[i][0])) {
                graph.put(edges[i][0], new ArrayList());
            }
            graph.get(edges[i][0]).add(new Node(edges[i][1], succProb[i]));

            if(!graph.containsKey(edges[i][1])) {
                graph.put(edges[i][1], new ArrayList());
            }
            graph.get(edges[i][1]).add(new Node(edges[i][0], succProb[i]));
        }

        PriorityQueue<Node> queue = new PriorityQueue<Node>(n, (a, b) -> (a.prob > b.prob ? -1 : 1));
        queue.add(new Node(start, 1.0));
        double[] probs = new double[n];
        probs[start] = 1.0;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int src = node.index;
            if(src == end) {
                return probs[end];
            }

            if(graph.containsKey(src)) {
                for(Node nei : graph.get(src)) {
                    if(probs[nei.index] < nei.prob * probs[src]) {
                        probs[nei.index] = nei.prob * probs[src];
                        queue.add(new Node(nei.index, probs[nei.index]));
                    }
                }
            }
            /*double maxProb = 0.0;
            int maxIndex = 0;
            for(int i = 0; i < probs.length; i++) {
                if(visited[i] || probs[i] == 0.0) {
                    continue;
                }
                if(probs[i] > maxProb) {
                    maxProb = probs[i];
                    maxIndex = i;
                }
            }
            queue.add(new Node(maxIndex, probs[maxIndex]));*/
        }

        /*for(int i = 0; i < probs.length; i++) {
            System.out.print(probs[i] + " ");
        }*/

        return probs[end];
    }

    class Node {
        private int index;
        private double prob;

        Node(int index, double prob) {
            this.index = index;
            this.prob = prob;
        }
    }
}
