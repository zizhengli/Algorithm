package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javafx.util.Pair;

/*
 * Using an example of Leetcode787 to show a graph search by using BFS
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 *
 * T = O(E*K) E is number of edges, K is number of stop
 * */
public class BFSPattern {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if(flights == null || flights.length == 0) {
            return 0;
        }
        int[][] adjMatrix = new int[n][n];
        for(int i = 0; i < flights.length; i++) {
            adjMatrix[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        Map<Pair<Integer, Integer>, Integer> distance = new HashMap();
        distance.put(new Pair(src, 0), 0);

        Queue<Integer> queue = new LinkedList();
        queue.add(src);
        int stops = 0;
        int ans = Integer.MAX_VALUE;
        while(!queue.isEmpty() && stops < K + 1) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Integer current = queue.poll();
                for(int nei = 0; nei < n; nei++) {
                    if(adjMatrix[current][nei] > 0) {
                        int distCurr = distance.getOrDefault(new Pair<Integer, Integer>(current, stops), Integer.MAX_VALUE);
                        int distNei = distance.getOrDefault(new Pair<Integer, Integer>(nei, stops + 1),
                                Integer.MAX_VALUE);
                        int dist = adjMatrix[current][nei];
                        if(stops == K && nei != dst) {
                            continue;
                        }

                        if(distCurr + dist < distNei) {
                            queue.add(nei);
                            distance.put(new Pair<Integer, Integer>(nei, stops + 1), distCurr + dist);
                            if(nei == dst) {
                                ans = Math.min(ans, distCurr + dist);
                            }
                        }
                    }
                }
            }
            stops++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
