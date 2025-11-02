package graph.scc;

import graph.Graph;
import java.util.*;

public class SCCFinder {
    private Graph g;
    private int time = 0;
    private int[] disc, low;
    private boolean[] stackMember;
    private Deque<Integer> stack = new ArrayDeque<>();
    private List<List<Integer>> components = new ArrayList<>();

    public SCCFinder(Graph g) {
        this.g = g;
        int n = g.n;
        disc = new int[n];
        low = new int[n];
        stackMember = new boolean[n];
        Arrays.fill(disc, -1);
    }

    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < g.n; i++) {
            if (disc[i] == -1) dfs(i);
        }
        return components;
    }

    private void dfs(int u) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        stackMember[u] = true;

        for (Graph.Edge e : g.adj.get(u)) {
            int v = e.v;
            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (stackMember[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int v = stack.pop();
                stackMember[v] = false;
                comp.add(v);
                if (v == u) break;
            }
            components.add(comp);
        }
    }
}