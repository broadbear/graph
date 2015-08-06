package org.mike.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SequentialGraph {
	Graph graph;
	Queue<Integer> queue = new LinkedList<Integer>();
	Integer[] distance;
	Color[] color;
	Integer[] pi;
	
	enum Color {
		WHITE,
		GREY,
		BLACK
	}
	
	SequentialGraph(Graph graph) {
		this.graph = graph;
		distance = new Integer[graph.size()];
		color = new Color[graph.size()];
		pi = new Integer[graph.size()];
	}
	
	Integer[] mooresShortestPath(int s) {
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
		return distance;		
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
	
	Integer[] bfs(int s) {
		for (int u = 0; u < graph.size(); u++) {
			color[u] = Color.WHITE;
			distance[u] = Integer.MAX_VALUE;
			pi[u] = 0;
		}
		color[s] = Color.GREY;
		distance[s] = 0;
		pi[s] = 0;
		queue.add(s);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Integer v: graph.outEdges(u)) {
				if (color[v] == Color.WHITE) {
					color[v] = Color.GREY;
					distance[v] = distance[u] + 1;
					pi[v] = u;
					queue.add(v);
				}
			}
			color[u] = Color.BLACK;
		}
		return pi;
	}
	
	Integer[] dfs(int s) {
		for (int u = 0; u < graph.size(); u++) {
			color[u] = Color.WHITE;
			distance[u] = Integer.MAX_VALUE;
			pi[u] = 0;
		}
		color[s] = Color.GREY;
		distance[s] = 0;
		pi[s] = 0;
		visit(s);
		return pi;
	}
	
	void visit(Integer u) {
		color[u] = Color.GREY;
		for (Integer v: graph.outEdges(u)) {
			visit(v);
		}
		color[u] = Color.BLACK;
	}
}
