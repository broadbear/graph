package org.mike.graph;

import java.io.BufferedReader;
import java.io.FileReader;


public class TestScript {

	public static void main(String[] args) {
//		Graph graph = createGraph();
		Graph graph = createGraph("input/tinyDG.txt");
		long start = System.currentTimeMillis();
		SequentialGraph seqG = new SequentialGraph(graph);
		Integer[] distance = seqG.mooresShortestPath(0);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		print(distance);

		start = System.currentTimeMillis();
		ParallelGraph parG = new ParallelGraph(graph, 4);
		distance = parG.mooresShortestPath(0);
		end = System.currentTimeMillis();
		System.out.println(end - start);
		print(distance);
		
		start = System.currentTimeMillis();
		SequentialGraph dfs = new SequentialGraph(graph);
		distance = dfs.bfs(0);
		end = System.currentTimeMillis();
		System.out.println(end - start);
		print(distance);
		
		start = System.currentTimeMillis();
		SequentialGraph bfs = new SequentialGraph(graph);
		distance = bfs.bfs(0);
		end = System.currentTimeMillis();
		System.out.println(end - start);
		print(distance);		
		
		start = System.currentTimeMillis();
		SequentialGraph dijk = new SequentialGraph(graph);
		distance = dijk.dijkstrasShortestPath(0);
		end = System.currentTimeMillis();
		System.out.println("dijkstra: "+(end - start));
		print(distance);		
		
		start = System.currentTimeMillis();
		SequentialGraph aStar = new SequentialGraph(graph);
		distance = aStar.aStarShortestPath(0, 12);
		end = System.currentTimeMillis();
		System.out.println("a*: "+(end - start));
		print(distance);		
	}
	
	static void print(Integer[] d) {
		for (int i = 0; i < d.length; i++) {
			System.out.println("["+i+"] "+d[i]);
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
			String line = br.readLine(); // # Vertices
			g = new AdjacencyList(Integer.parseInt(line));
			br.readLine(); // # Edges
			while((line = br.readLine()) != null) {
				String[] lineSplit = line.split(" ");
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
