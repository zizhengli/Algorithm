package special;

import utils.Print;

/**
 * UnionFind algorithm is used to determine whether a undirected graph contains circle or not.
 */
public class UnionFind {

    private static int find(int[] parent, int index) {
        if(parent[index] == -1) {
            return index;
        }
        return find(parent, parent[index]);
    }

    private static void union(int[] parent, int x, int y) {
        int xParent = find(parent, x);
        int yParent = find(parent, y);
        parent[xParent] = yParent;
    }

    static boolean isCycle(Graph graph) {
        int[] parent = new int[graph.verticesNum];
        // Initialize parent array
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

        for(int i = 0; i < graph.edges.length; i++) {
            // find parent of two vertices of each edge
            int x = find(parent, graph.edges[i].start);
            int y = find(parent, graph.edges[i].end);

            if(x == y) {
                return true;
            }
            union(parent, x, y);
            Print.print(parent);
        }
        return false;
    }

    private static class Graph {
        int verticesNum;
        Edge[] edges;
        Graph(int verticesNum, int edgeNum) {
            this.edges = new Edge[edgeNum];
            for(int i = 0; i < this.edges.length; i++) {
                this.edges[i] = new Edge();
            }
            this.verticesNum = verticesNum;
        }
        void add(int edge, int start, int end) {
            this.edges[edge].start = start;
            this.edges[edge].end = end;
        }
    }

    private static class Edge {
        int start;
        int end;
    }

    public static void main(String[] args) {

        Graph graph = new Graph(3, 3);
        graph.add(0, 0, 1);
        graph.add(1, 1, 2);
        graph.add(2, 2, 2);

        System.out.println(isCycle(graph));
    }
}
