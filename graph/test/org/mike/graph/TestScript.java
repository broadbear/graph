package org.mike.graph;

import java.io.BufferedReader;
import java.io.FileReader;


public class TestScript {

	public static void main(String[] args) {
//		Graph graph = createGraph();
		Graph graph = createGraph("input/mediumG.txt");
//		SequentialGraph g = new SequentialGraph(graph);
		ParallelGraph g = new ParallelGraph(graph, 1);
		Integer[] distance = g.mooresShortestPath(0);
		for (int i = 0; i < distance.length; i++) {
			System.out.println("["+i+"] "+distance[i]);
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
	
	static Graph createGraph(String fileName) {
		Graph g;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			String[] lineSplit = line.split(" ");
			g = new AdjacencyList(Integer.parseInt(lineSplit[0]));
			while((line = br.readLine()) != null) {
				lineSplit = line.split(" ");
				g.addEdge(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]), 1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("trouble");
		}
		return g;
	}
}
