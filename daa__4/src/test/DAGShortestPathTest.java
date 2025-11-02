import graph.dagsp.DAGShortestPath;
import graph.utils.Metrics;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGShortestPathTest {
    @Test
    public void testShortestPath() {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        adj.put(0, List.of(new int[]{1, 1}, new int[]{2, 4}));
        adj.put(1, List.of(new int[]{2, 2}, new int[]{3, 6}));
        adj.put(2, List.of(new int[]{3, 3}));
        adj.put(3, List.of());
        DAGShortestPath sp = new DAGShortestPath(adj, 4);
        List<Integer> topo = List.of(0, 1, 2, 3);
        Map<Integer, Integer> dist = sp.shortestPath(0, topo);
        assertEquals(0, dist.get(0));
        assertEquals(1, dist.get(1));
        assertEquals(3, dist.get(2));
        assertEquals(6, dist.get(3));
    }
}