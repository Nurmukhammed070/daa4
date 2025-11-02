Assignment 4 – Smart City / Smart Campus Scheduling
Abdikarim Nurmukhammed, SE-2430

Overview
This project combines three algorithms:
1. Strongly Connected Components (Tarjan’s Algorithm)
2. Topological Sort (DFS-based)
3. Shortest & Longest Paths in DAG

Goal: detect cyclic dependencies in city-service tasks, compress them, and find optimal (shortest & longest) scheduling paths.


Folder Structure

src/         → Source code
src/test/    → JUnit tests
data/        → Input datasets
README.md    → This report

Algorithms

1. Strongly Connected Components (Tarjan)
	•	Detects cycles and groups them.
	•	Builds condensation DAG.

2. Topological Sort
	•	Produces valid order of SCCs and tasks.
	•	Uses DFS (simple and efficient).

3. Shortest and Longest Paths
	•	Runs only on the condensation DAG (acyclic).
	•	Uses topological order for dynamic programming.

Metrics

Each algorithm measures:
	•	DFS visits
	•	Edges processed
	•	Pushes/Pops (for Topo)
	•	Relaxations (for DAG paths)
	•	Execution time in nanoseconds

Example output:

=== SCC ===
[2, 1, 0] size=3
[3] size=1
...
DFS visits: 8 | Time: 128834 ns

Datasets

Name	Vertices	Edges	Type	Description
small1.json	6	8	Mixed	Simple graph with 1 cycle
medium1.json	15	25	Cyclic	Several SCCs
large1.json	40	90	DAG	Performance test

Stored in /data/.


Results Summary

Algorithm	Metric	Avg Time (ns)	Comments
Tarjan SCC	DFS visits	~10	Small graphs fast
Topo Sort	Edges	~5	Linear time
DAG Shortest	Relaxations	~4	Efficient
DAG Longest	Relaxations	~4	Similar cost


Analysis
	•	SCC handles cyclic dependencies efficiently.
	•	Topological sort provides a valid processing order.
	•	DAG shortest paths are optimal for scheduling independent tasks.
	•	For dense graphs, time increases linearly with edge count.


Conclusion
	•	Tarjan’s SCC is ideal for detecting cycles.
	•	Topological sort efficiently orders components.
	•	DAG shortest/longest path finds optimal schedules.
	•	This project can be extended to real smart city data.

Tests

JUnit files test small deterministic graphs under:
	•	/src/test/
Run with:

mvn test
