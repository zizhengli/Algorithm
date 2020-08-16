import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import javafx.util.Pair;

/**
 * Created by lim20 on 11/12/2018.
 */
public class Test {




    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        Map<String, List<Node>> map = new HashMap();
        for(int i = 0; i < equations.length; i++) {
            if(map.containsKey(equations[i][0])) {
                map.get(equations[i][0]).add(new Node(equations[i][1], values[i]));
                // Reverse edge
            } else {
                List<Node> list = new ArrayList();
                list.add(new Node(equations[i][1], values[i]));
                map.put(equations[i][0], list);
            }

            // Reverse edge
            if(map.containsKey(equations[i][1])) {
                map.get(equations[i][1]).add(new Node(equations[i][0], Math.pow(values[i], -1)));
            } else {
                List<Node> list1 = new ArrayList();
                list1.add(new Node(equations[i][0], Math.pow(values[i], -1)));
                map.put(equations[i][1], list1);
            }
        }
        //System.out.println(map);
        Map<Pair<String, String>, Double> memo = new HashMap();

        double[] result = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            result[i] = dfs(queries[i][0], queries[i][1], map, 1.0, memo);
        }
        return result;
    }

    private Double dfs(String curr, String end, Map<String, List<Node>> graph, double currRes, Map<Pair<String, String>, Double> memo) {
        Pair<String, String> pair = new Pair(curr, end);
        if(memo.containsKey(pair)) {
            return memo.get(pair);
        }

        if(!graph.containsKey(curr) || graph.get(curr).size() == 0) {
            return -1.0;
        }

        if(curr.equals(end)) {
            return 1.0 * currRes;
        }
        Double result = null;
        for(Node nei : graph.get(curr)) {
            //System.out.println(curr + "_" + nei.dest + "_" + currRes * nei.weight);
            result = dfs(nei.dest, end, graph, currRes * nei.weight, memo);
            if(result != null) {
                memo.put(pair, result);
                return result;
            }
        }
        memo.put(pair, null);
        return null;
    }

    class Node {
        String dest;
        double weight;

        Node(String dest, double weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        char[][] board = {{'o','a','a','n'},
                            {'e','t','a','e'},
                            {'i','h','k','r'},
                            {'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};


        Test test = new Test();


        System.out.println( 2/2 );



        System.out.println(201 % 100);

        Integer int1 = new Integer(1);
        Integer int2 = new Integer(2);
        System.out.println(int1 - int2);
        int k = 3;
        double tesstss = k % 2 == 0 ? 0.1 : 0.2;

        boolean[] booean1 = new boolean[1];
        System.out.println(booean1[0]);
    }
}
