package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UndirectedGraph<T> {

    private Map<T, ArrayList<Edge<T>>> vertexMap;

    public UndirectedGraph() {
        vertexMap = new HashMap<>();
    }

    public boolean addVertex(T v) {
        if(vertexMap.containsKey(v)) {
            return false;
        }
        vertexMap.put(v, new ArrayList<Edge<T>>());
        return true;
    }

    public boolean addEdge(T src, T dest) {
        if(src == null || dest == null
                || !vertexMap.containsKey(src) || !vertexMap.containsKey(dest)) {
            return false;
        }
        vertexMap.get(src).add(new Edge(src, dest));
        //vertexMap.get(dest).add(new Edge(dest, src));
        return true;
    }

    public int size() {
        return vertexMap.size();
    }

    public ArrayList<Edge<T>> getEdges(T v) {
        return vertexMap.get(v);
    }

    public Set<T> getVertices() {
        return vertexMap.keySet();
    }

    public class Edge<T> {

        T source;
        T destination;

        public Edge(T src, T dest) {
            source = src;
            destination = dest;
        }

        public T getSource() {
            return source;
        }

        public T getDestination() {
            return destination;
        }
    }
}
