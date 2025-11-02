package graph.dagsp;

import java.util.*;
import graph.utils.Metrics;

public class DAGLongestPath {

    public static class Result {
        public final long[] dist;
        public final int[] parent;

        public Result(long[] dist, int[] parent) {
            this.dist = dist;
            this.parent = parent;
        }

        public List<Integer> reconstruct(int target) {
            LinkedList<Integer> path = new LinkedList<>();
            if (dist[target] <= Long.MIN_VALUE / 4) return path; // unreachable
            int cur = target;
            while (cur != -1) {
                path.addFirst(cur);
                cur = parent[cur];
            }
            return path;
        }
    }

    public static Result compute(int n, Map<Integer, List<int[]>> adj, List<Integer> topo) {
        long[] dist = new long[n];
        int[] parent = new int[n];
        Arrays.fill(dist, Long.MIN_VALUE / 4);
        Arrays.fill(parent, -1);

        boolean[] hasIncoming = new boolean[n];
        for (List<int[]> edges : adj.values()) {
            for (int[] e : edges) hasIncoming[e[0]] = true;
        }
        for (int i = 0; i < n; i++) if (!hasIncoming[i]) dist[i] = 0;

        for (int u : topo) {
            if (dist[u] <= Long.MIN_VALUE / 8) continue; // unreachable
            for (int[] e : adj.getOrDefault(u, Collections.emptyList())) {
                int v = e[0];
                int w = e[1];
                long nd = dist[u] + w;
                if (nd > dist[v]) {
                    dist[v] = nd;
                    parent[v] = u;
                }
            }
        }
        return new Result(dist, parent);
    }

    public static int findCriticalEnd(long[] dist) {
        int best = -1;
        long bestVal = Long.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] > bestVal) {
                bestVal = dist[i];
                best = i;
            }
        }
        return best;
    }
}