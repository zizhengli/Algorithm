package unionfind;

import java.util.Iterator;
import java.util.List;

import utils.UndirectedGraph;

public class FindCyclicGraph {

    public boolean isCyclic(UndirectedGraph<Integer> undirectedGraph) {
        if(undirectedGraph == null) {
            return false;
        }
        int[] parent = new int[undirectedGraph.size()];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

        Iterator<Integer> ite = undirectedGraph.getVertices().iterator();
        while(ite.hasNext()) {
            List<UndirectedGraph<Integer>.Edge<Integer>> edges = undirectedGraph.getEdges(ite.next());
            for(UndirectedGraph.Edge edge : edges) {
                int parentX = find(parent, (Integer)edge.getSource());
                int parentY = find(parent, (Integer)edge.getDestination());
                if(parentX == parentY) {
                    return true;
                }
                union(parent, (Integer)edge.getSource(), (Integer)edge.getDestination());
            }
        }
        return false;
    }

    private int find(int[] parent, int i) {
        if(parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    private void union(int[] parent, int i, int j) {
        int parentI = find(parent, i);
        int parentJ = find(parent, j);
        parent[parentI] = parentJ;
    }

    public static void main(String[] args) {

        UndirectedGraph<Integer> graph = new UndirectedGraph<>();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        //graph.addEdge(2, 0);

        FindCyclicGraph test = new FindCyclicGraph();
        System.out.println(test.isCyclic(graph));

    }
}
