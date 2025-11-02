import graph.scc.TarjanSCC;
import graph.utils.Metrics;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TarjanSCCTest {

    @Test
    public void testSimpleSCC() {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, List.of(new int[]{1, 1}));
        graph.put(1, List.of(new int[]{2, 1}));
        graph.put(2, List.of(new int[]{0, 1}));

        Metrics metrics = new Metrics();
        TarjanSCC tarjan = new TarjanSCC(graph, 3, metrics);
        List<List<Integer>> sccs = tarjan.run();

        assertEquals(1, sccs.size());
        List<Integer> component = sccs.get(0);
        assertTrue(component.containsAll(List.of(0, 1, 2)));
    }

    @Test
    public void testTwoSCCs() {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, List.of(new int[]{1, 1}));
        graph.put(1, List.of(new int[]{2, 1}));
        graph.put(2, List.of(new int[]{0, 1}, new int[]{3, 1}));
        graph.put(3, List.of(new int[]{4, 1}));
        graph.put(4, List.of(new int[]{3, 1}));

        Metrics metrics = new Metrics();
        TarjanSCC tarjan = new TarjanSCC(graph, 5, metrics);
        List<List<Integer>> sccs = tarjan.run();

        assertEquals(2, sccs.size());
    }
}