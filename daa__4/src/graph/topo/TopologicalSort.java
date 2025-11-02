package graph.topo;

import graph.utils.Metrics;
import java.util.*;

public class TopologicalSort {

    private final Map<Integer, List<int[]>> graph;
    private final int n;
    private final Metrics metrics;

    public TopologicalSort(Map<Integer, List<int[]>> graph, int n, Metrics metrics) {
        this.graph = graph;
        this.n = n;
        this.metrics = metrics;
    }

    public List<Integer> run() {
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private void dfs(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;

        List<int[]> neighbors = graph.getOrDefault(node, new ArrayList<>());

        for (int[] edge : neighbors) {
            int neighbor = edge[0];
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }
}