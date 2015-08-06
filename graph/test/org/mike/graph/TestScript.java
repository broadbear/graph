
package org.mike.graph;

import java.io.BufferedReader;
import java.io.FileReader;


public class TestScript {

	public static void main(String[] args) {
		Graph graph = createGraph();
//		Graph graph = createGraph("input/tinyDG.txt");
		long start = System.currentTimeMillis();
		SequentialGraph seqG = new SequentialGraph(graph);
		Integer[] distance = seqG.mooresShortestPath(0);
		long end = System.currentTimeMillis();
		System.out.println("moores: "+(end - start));
		print(distance);

		start = System.currentTimeMillis();
		ParallelGraph parG = new ParallelGraph(graph, 4);
		distance = parG.mooresShortestPath(0);
		end = System.currentTimeMillis();
		System.out.println("parG: "+(end - start));
		print(distance);
		
		start = System.currentTimeMillis();
		SequentialGraph dfs = new SequentialGraph(graph);
		distance = dfs.depthFirstSearch(0);
		end = System.currentTimeMillis();
		System.out.println("dfs: "+(end - start));
		print(distance);
		
		start = System.currentTimeMillis();
		SequentialGraph bfs = new SequentialGraph(graph);
		distance = bfs.breadthFirstSearch(0);
		end = System.currentTimeMillis();
		System.out.println("bfs: "+(end - start));
		print(distance);		
		
		start = System.currentTimeMillis();
		SequentialGraph dijk = new SequentialGraph(graph);
		distance = dijk.dijkstrasShortestPath(0);
		end = System.currentTimeMillis();
		System.out.println("dijkstra: "+(end - start));
		print(distance);		
		
		start = System.currentTimeMillis();
		SequentialGraph bellmanFord = new SequentialGraph(createGraph());
		distance = bellmanFord.bellmanFordShortestPath(0);
		end = System.currentTimeMillis();
		System.out.println("bellman-ford: "+(end - start));
		print(distance);
		
		start = System.currentTimeMillis();
		SequentialGraph floydWarshall = new SequentialGraph(createGraph());
		int[][] fwDistance = floydWarshall.floydWarshallAllPairsShortestPath();
		end = System.currentTimeMillis();
		System.out.println("floyd-warshall: "+(end - start));
		print(fwDistance[0]);
		
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
	
	static void print(int[] d) {
		for (int i = 0; i < d.length; i++) {
			System.out.println("["+i+"] "+d[i]);
		}
	}

	/**
	 *    4     3     1
	 * 0 --> 1 --> 3 --> 4
	 *   \   /\
	 *  1 \  | 2
	 *    _\||
	 *       2
	 *      
	 * d = { 0, 3, 1, 7, 8 }
	 */
	static Graph createGraph() {
		Graph graph = new AdjacencyList(5);
		graph.addEdge(0, 1, 4);
		graph.addEdge(0, 2, 1);
		graph.addEdge(1, 3, 3);
		graph.addEdge(2, 1, 2);
		graph.addEdge(3, 4, 1);
		return graph;
	}

	/**
	 * From: http://www.youtube.com/watch?v=L6x53Vjy_HM
	 * 
	 * Notice the negative edge weight!
	 * 
	 * d = { 0, 1, 2, 6, 7, 5, 3 }
	 * 
	 * @return
	 */
	static Graph createGraph2() {
		Graph graph = new AdjacencyList(7);
		graph.addEdge(0, 1, 2); // S -> A
		graph.addEdge(0, 6, 3); // S -> F
		graph.addEdge(1, 2, 1); // A -> B
		graph.addEdge(1, 6, 3); // A -> F
		graph.addEdge(2, 3, 4); // B -> C
		graph.addEdge(2, 6, 1); // B -> F
		graph.addEdge(4, 2, 2); // D -> B
		graph.addEdge(4, 3, 1); // D -> C
		graph.addEdge(5, 2, 3); // E -> B
		graph.addEdge(5, 4, 2); // E -> D
		graph.addEdge(6, 1,-2); // F -> A
		graph.addEdge(6, 5, 2); // F -> E
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
