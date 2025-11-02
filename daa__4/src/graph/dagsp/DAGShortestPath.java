package graph.dagsp;

import java.util.*;
import graph.utils.Metrics;

public class DAGShortestPath {
    private Map<Integer, List<int[]>> adj;
    private int n;

    public DAGShortestPath(Map<Integer, List<int[]>> adj, int n) {
        this.adj = adj;
        this.n = n;
    }

    public Map<Integer, Integer> shortestPath(int src, List<Integer> topo) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 0; i < n; i++) dist.put(i, Integer.MAX_VALUE);
        dist.put(src, 0);

        for (int u : topo) {
            if (dist.get(u) != Integer.MAX_VALUE) {
                for (int[] e : adj.getOrDefault(u, List.of())) {
                    int v = e[0], w = e[1];
                    if (dist.get(v) > dist.get(u) + w)
                        dist.put(v, dist.get(u) + w);
                }
            }
        }
        return dist;
    }
}