package graph.utils;

public class Metrics {
    private long startTime;
    private long endTime;
    private int dfsVisits;
    private int edgesProcessed;
    private int pushes;
    private int pops;
    private int relaxations;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void incDFS() { dfsVisits++; }
    public void incEdge() { edgesProcessed++; }
    public void incPush() { pushes++; }
    public void incPop() { pops++; }
    public void incRelax() { relaxations++; }

    public void print(String name) {
        System.out.println("\n=== " + name + " Metrics ===");
        System.out.println("DFS visits: " + dfsVisits);
        System.out.println("Edges processed: " + edgesProcessed);
        System.out.println("Pushes: " + pushes);
        System.out.println("Pops: " + pops);
        System.out.println("Relaxations: " + relaxations);
        System.out.println("Time (ns): " + (endTime - startTime));
    }
}