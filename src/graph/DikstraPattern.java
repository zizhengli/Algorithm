package graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DikstraPattern {

    /**
     * N^2 logN N is number of cities.
     * If we can keep all nodes inside PQ and update them, the time complexity will be down to
     * (M + N) logM,  M is number of cities, n is number of edges.
     *
     *
     *
     * Context: queue and map (to store value by node)
     *
     * 1. push first node into queue
     * 2. update map with first node
     * 3. in Queue while, mark the current node to true;
     * 4. Iterate over all neighbors of the current node
     * 5. inside the loop, update map based on the condition of the question (it can be min or max value)
     * 6. Iterate over all items of map to get min or max value, push it into queue.
     *    optimization : PriorityQueue can be used to find min or max value. N -> logN
     *
     * 7. loop back to step1.
     *
     *
     * */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        int adjMatrix[][] = new int[n][n];
        for (int[] flight: flights) {
            adjMatrix[flight[0]][flight[1]] = flight[2];
        }

        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        int[] steps = new int[n];
        Arrays.fill(steps, Integer.MAX_VALUE);

        // int[0] = src, int[1] = step, int[2] = cost
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> (a[2] - b[2]));
        queue.add(new int[] {src, 0, 0});
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currStop = curr[0];
            int stops = curr[1];
            int cost = curr[2];

            if(currStop == dst) {
                return cost;
            }
            if(stops == K + 1) {
                continue;
            }

            for(int nei = 0; nei < n; nei++) {
                if(adjMatrix[currStop][nei] > 0) {
                    if(cost + adjMatrix[currStop][nei] < costs[nei] || stops < steps[nei]) {
                        costs[nei] = cost + adjMatrix[currStop][nei];
                        queue.add(new int[] {nei, stops + 1, cost + adjMatrix[currStop][nei]});
                        steps[nei] = stops;
                    }
                }
            }
        }
        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }

}
