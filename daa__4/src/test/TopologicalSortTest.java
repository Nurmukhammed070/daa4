import graph.topo.TopologicalSort;
import graph.utils.Metrics;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    @Test
    public void testTopoOrder() {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, List.of(new int[]{1, 1}));
        graph.put(1, List.of(new int[]{2, 1}));
        graph.put(2, new ArrayList<>());

        Metrics metrics = new Metrics();
        TopologicalSort topo = new TopologicalSort(graph, 3, metrics);
        List<Integer> order = topo.run();

        assertEquals(List.of(0, 1, 2), order);
    }

    @Test
    public void testTopoDisconnected() {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, List.of(new int[]{1, 1}));
        graph.put(1, new ArrayList<>());
        graph.put(2, new ArrayList<>());

        Metrics metrics = new Metrics();
        TopologicalSort topo = new TopologicalSort(graph, 3, metrics);
        List<Integer> order = topo.run();

        assertTrue(order.indexOf(0) < order.indexOf(1));
        assertTrue(order.contains(2));
    }
}