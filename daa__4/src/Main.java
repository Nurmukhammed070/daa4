import java.util.*;
import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import graph.utils.Metrics;

public class Main {
    public static void main(String[] args) {
        int n = 8;
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) adj.put(i, new ArrayList<>());

        adj.get(0).add(new int[]{1, 1});
        adj.get(1).add(new int[]{2, 1});
        adj.get(2).add(new int[]{0, 1});
        adj.get(2).add(new int[]{3, 1});
        adj.get(3).add(new int[]{4, 2});
        adj.get(4).add(new int[]{5, 2});
        adj.get(5).add(new int[]{6, 1});
        adj.get(6).add(new int[]{7, 1});

        Metrics sccM = new Metrics();
        TarjanSCC scc = new TarjanSCC(adj, n, sccM);
        List<List<Integer>> comps = scc.run();
        System.out.println("=== SCC ===");
        for (List<Integer> comp : comps) {
            System.out.println(comp + " size=" + comp.size());
        }
        sccM.print("SCC");

        int[] cid = scc.getComponentIds();
        int compCount = comps.size();
        Map<Integer, List<int[]>> dag = new HashMap<>();
        for (int i = 0; i < compCount; i++) dag.put(i, new ArrayList<>());

        for (int u = 0; u < n; u++) {
            for (int[] e : adj.get(u)) {
                int v = e[0];
                int cu = cid[u], cv = cid[v];
                if (cu != cv) {
                    dag.get(cu).add(new int[]{cv, e[1]});
                }
            }
        }

        Metrics topoM = new Metrics();
        TopologicalSort topo = new TopologicalSort(dag, compCount, topoM);
        List<Integer> order = topo.run();
        System.out.println("\n=== Topological Order of Components ===");
        System.out.println(order);
        topoM.print("Topological Sort");

        Metrics spM = new Metrics();
        spM.start();
        int source = 4;
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 0; i < n; i++) dist.put(i, Integer.MAX_VALUE);
        dist.put(source, 0);

        for (int u : order) {
            for (int[] e : dag.getOrDefault(u, List.of())) {
                int v = e[0];
                int w = e[1];
                if (dist.get(u) != Integer.MAX_VALUE && dist.get(v) > dist.get(u) + w) {
                    dist.put(v, dist.get(u) + w);
                    spM.incRelax();
                }
            }
        }
        spM.stop();

        System.out.println("\n=== Shortest Paths from " + source + " ===");
        for (int i = 0; i < n; i++) {
            System.out.println(i + ": " + dist.get(i));
        }
        spM.print("Shortest Path");

        Metrics lpM = new Metrics();
        lpM.start();
        Map<Integer, Integer> longDist = new HashMap<>();
        for (int i = 0; i < n; i++) longDist.put(i, Integer.MIN_VALUE);
        longDist.put(source, 0);

        for (int u : order) {
            for (int[] e : dag.getOrDefault(u, List.of())) {
                int v = e[0];
                int w = e[1];
                if (longDist.get(u) != Integer.MIN_VALUE && longDist.get(v) < longDist.get(u) + w) {
                    longDist.put(v, longDist.get(u) + w);
                    lpM.incRelax();
                }
            }
        }
        lpM.stop();

        System.out.println("\n=== Longest Paths from " + source + " ===");
        for (int i = 0; i < n; i++) {
            System.out.println(i + ": " + longDist.get(i));
        }
        lpM.print("Longest Path");
    }
}