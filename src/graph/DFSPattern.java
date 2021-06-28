package graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

/*
* Using an example of Leetcode787 to show a graph search by using DFS
* https://leetcode.com/problems/cheapest-flights-within-k-stops/
*
*  T = O(V^2 * K) V is number of cities, K is number of stop
* */
public class DFSPattern {

    private Integer res = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        Map<Integer, List<Node>> graph = new HashMap();
        for(int i = 0; i < flights.length; i++) {
            if(!graph.containsKey(flights[i][0])) {
                graph.put(flights[i][0], new ArrayList<Node>());
            }
            graph.get(flights[i][0]).add(new Node(flights[i][1], flights[i][2]));
        }
        Map<Pair<Integer, Integer>, Integer> memo = new HashMap();
        int ans = dfsWithMemo(graph, src, dst, K + 1, memo);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /*
    * Using memo
    * */
    private int dfsWithMemo(Map<Integer, List<Node>> graph, int current, int target, int stops,
                            Map<Pair<Integer, Integer>, Integer> memo) {
        Pair<Integer, Integer> key = new Pair(current, stops);
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        if(current == target) {
            return 0;
        }
        if(stops == 0) {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        if(graph.containsKey(current)) {
            for(Node nei : graph.get(current)) {
                int cost = dfsWithMemo(graph, nei.dest, target, stops - 1, memo);
                if(cost != -1) {
                    min = Math.min(min, cost + nei.cost);
                }
            }
        }
        if(min == Integer.MAX_VALUE) {
            return -1;
        }
        memo.put(key, min);
        return min;
    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {

        Map<Integer, List<Node>> graph = new HashMap();
        for(int i = 0; i < flights.length; i++) {
            if(!graph.containsKey(flights[i][0])) {
                graph.put(flights[i][0], new ArrayList<Node>());
            }
            graph.get(flights[i][0]).add(new Node(flights[i][1], flights[i][2]));
        }
        boolean[] visited = new boolean[n];
        visited[src] = true;

        dfsWithTrimmingPath(graph, src, dst, K + 1, visited, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfsWithTrimmingPath(Map<Integer, List<Node>> graph, int current, int target,
                                     int stops, boolean[] visited, int cost) {
        if(current == target) {
            res = cost;
            return;
        }
        if(stops == 0) {
            return;
        }
        int min = Integer.MAX_VALUE;
        if(graph.containsKey(current)) {
            for(Node nei : graph.get(current)) {
                if(visited[nei.dest] || cost + nei.cost > res) {
                    continue;
                }
                visited[nei.dest] = true;
                dfsWithTrimmingPath(graph, nei.dest, target, stops - 1, visited, cost + nei.cost);
                visited[nei.dest] = false;
            }
        }
    }

    private class Node {
        int dest;
        int cost;
        Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
        public String toString() {
            return this.dest + " " + this.cost;
        }
    }
}
