package org.mike.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SequentialGraph {
	Graph graph;
	Queue<Integer> queue = new LinkedList<Integer>();
	Integer[] distance;
	
	SequentialGraph(Graph graph) {
		this.graph = graph;
		distance = new Integer[graph.size()];
	}
	
	void mooresShortestPath(int s) {
		distance[s] = 0;
		for (int i = 0; i < graph.size(); i++) {
			if (i != s) {
				distance[i] = Integer.MAX_VALUE;
			}
		}
		queue.add(s);
		while (queue.size() != 0) {
			search();
		}
		
	}
	
	void search() {
		Integer u = queue.poll();
		for (Integer v: graph.outEdges(u)) {
			int newDistance = distance[u] + graph.getWeight(u, v);
			if (newDistance < distance[v]) {
				distance[v] = newDistance;
				if (!queue.contains(v)) {
					queue.add(v);
				}
			}
		}
	}
}
