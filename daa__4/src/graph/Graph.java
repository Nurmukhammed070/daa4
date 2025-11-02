package graph;

import java.util.*;

public class Graph {
    public int n;
    public boolean directed;
    public Map<Integer, List<Edge>> adj = new HashMap<>();

    public static class Edge {
        public int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u; this.v = v; this.w = w;
        }
    }

    public Graph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        for (int i = 0; i < n; i++) adj.put(i, new ArrayList<>());
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new Edge(u, v, w));
        if (!directed) adj.get(v).add(new Edge(v, u, w));
    }
}