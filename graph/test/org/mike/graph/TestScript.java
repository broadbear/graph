package org.mike.graph;


public class TestScript {

	public static void main(String[] args) {
		Graph graph = createGraph();
//		SequentialGraph2 g = new SequentialGraph2(graph);
		ParallelGraph g = new ParallelGraph(graph, 1);
		Integer[] distance = g.mooresShortestPath(0);
		for (int i = 0; i < distance.length; i++) {
			System.out.println(distance[i]);
		}
	}

	static Graph createGraph() {
		Graph graph = new AdjacencyList(5);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 3, 3);
		graph.addEdge(2, 1, 2);
		graph.addEdge(3, 4, 1);
		return graph;
	}
}
