package graph.scc;

import java.util.*;
import graph.utils.Metrics;

public class TarjanSCC {
    private final Map<Integer, List<int[]>> adj;
    private final int n;
    private final Metrics metrics;
    private int time = 0;
    private int[] ids, low;
    private boolean[] onStack;
    private Deque<Integer> stack;
    private List<List<Integer>> sccs;
    private int[] compId;

    public TarjanSCC(Map<Integer, List<int[]>> adj, int n, Metrics metrics) {
        this.adj = adj;
        this.n = n;
        this.metrics = metrics;
    }

    public List<List<Integer>> run() {
        metrics.start();
        ids = new int[n];
        low = new int[n];
        onStack = new boolean[n];
        stack = new ArrayDeque<>();
        sccs = new ArrayList<>();
        compId = new int[n];
        Arrays.fill(ids, -1);

        for (int i = 0; i < n; i++) {
            if (ids[i] == -1) dfs(i);
        }

        metrics.stop();
        return sccs;
    }

    private void dfs(int at) {
        metrics.incDFS();
        ids[at] = low[at] = time++;
        stack.push(at);
        onStack[at] = true;

        for (int[] e : adj.getOrDefault(at, List.of())) {
            metrics.incEdge();
            int to = e[0];
            if (ids[to] == -1) {
                dfs(to);
                low[at] = Math.min(low[at], low[to]);
            } else if (onStack[to]) {
                low[at] = Math.min(low[at], ids[to]);
            }
        }

        if (ids[at] == low[at]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int node = stack.pop();
                onStack[node] = false;
                comp.add(node);
                compId[node] = sccs.size();
                if (node == at) break;
            }
            sccs.add(comp);
        }
    }

    public int[] getComponentIds() { return compId; }
}