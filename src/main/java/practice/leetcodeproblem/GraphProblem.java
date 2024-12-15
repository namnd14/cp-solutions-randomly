package practice.leetcodeproblem;

import practice.technique.ShortestPathDijkstraAdjacencyList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphProblem {
    public void log() {
        System.out.println("Graph");
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> mapNameToNumber = new HashMap<>();
        List<List<AdjacencyNode>> graph = new ArrayList<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> currentEquation = equations.get(i);
            String first = currentEquation.get(0);
            String second = currentEquation.get(1);
            if (!mapNameToNumber.containsKey(first)) {
                mapNameToNumber.put(first, mapNameToNumber.size());
                graph.add(new ArrayList<>());
            }
            if (!mapNameToNumber.containsKey(second)) {
                mapNameToNumber.put(second, mapNameToNumber.size());
                graph.add(new ArrayList<>());
            }
        }

        for (int i = 0; i < equations.size(); i++) {
            List<String> currentEquation = equations.get(i);
            double value = values[i];
            String first = currentEquation.get(0);
            String second = currentEquation.get(1);
            int firstInt = mapNameToNumber.get(first);
            int secondInt = mapNameToNumber.get(second);


            List<AdjacencyNode> firstNode = graph.get(firstInt);
            List<AdjacencyNode> secondNode = graph.get(secondInt);

            firstNode.add(new AdjacencyNode(secondInt, value));
            secondNode.add(new AdjacencyNode(firstInt, 1.0 / value));
        }

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String first = queries.get(i).get(0);
            String second = queries.get(i).get(1);

            if (!mapNameToNumber.containsKey(first) || !mapNameToNumber.containsKey(second)) {
                result[i] = -1.0;
                continue;
            }

            if (first.equals(second)) {
                result[i] = 1.0;
                continue;
            }

            double[] allPaths = dijkstra(mapNameToNumber.size(), graph, mapNameToNumber.get(first));
            if (allPaths[mapNameToNumber.get(second)] != Double.MAX_VALUE) {
                result[i] = allPaths[mapNameToNumber.get(second)];
            } else {
                result[i] = -1.0;
            }
        }

        return result;
    }

    private double[] dijkstra(int size, List<List<AdjacencyNode>> graph, int src) {
        double[] distance = new double[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Double.MAX_VALUE;
        }
        distance[src] = 1.0;

        PriorityQueue<AdjacencyNode> pq = new PriorityQueue<>((v1, v2) -> Double.compare(v1.equation, v2.equation));
        pq.add(new AdjacencyNode(src, 0));

        while (pq.size() > 0) {
            AdjacencyNode current = pq.poll();

            for (AdjacencyNode n : graph.get(current.vertex)) {
                if (distance[current.vertex] * n.equation < distance[n.vertex]) {
                    distance[n.vertex] = n.equation * distance[current.vertex];
                    pq.add(new AdjacencyNode(n.vertex, distance[n.vertex]));
                }
            }
        }

        return distance;
    }

    public static class AdjacencyNode {
        public int vertex;
        public double equation;

        public AdjacencyNode(int vertex, double equation) {
            this.vertex = vertex;
            this.equation = equation;
        }
    }
}
