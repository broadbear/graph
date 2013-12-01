package org.mike.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
			if (color[v] == Color.WHITE) {
				visit(v);
			}
		}
		color[u] = Color.BLACK;
	}
	
	Integer[] dijkstrasShortestPath(int s) {
		for (int u = 0; u < graph.size(); u++) {
			distance[u] = Integer.MAX_VALUE;
			color[u] = Color.WHITE;
			pi[u] = 0;
		}
		distance[s] = 0;
		queue.add(s);
		while (!queue.isEmpty()) {
			Integer u = queue.poll(); // TODO: not priority-like
			color[u] = Color.BLACK; // 'visited'
			for (Integer v: graph.outEdges(u)) {
				int alt = distance[u] + graph.getWeight(u, v);
				if (alt < distance[v]) {
					distance[v] = alt;
					pi[v] = u;
					if (color[v] != Color.BLACK) {
						queue.add(v);
					}
				}
			}
		}
		return pi;
	}

	/**
	 * A* single shortest path algorithm. pi (previous) array has
	 * been substituted for the 'came_from' map.
	 * 
	 * @param start
	 * @param goal
	 * @return
	 */
	Integer[] aStarShortestPath(int start, int goal) {
		Set<Integer> closedSet = new HashSet<Integer>();
		Set<Integer> openSet = new HashSet<Integer>();
		Integer[] gScore = new Integer[graph.size()];
		Integer[] fScore = new Integer[graph.size()];

		openSet.add(start);
		gScore[start] = 0;
		fScore[start] = gScore[start] + heuristicCostEstimate(start, goal);
		while(!openSet.isEmpty()) {
			Integer current = getLowestFScore(openSet, fScore);
			if (current == goal) {
				return pi;
			}
			openSet.remove(current);
			closedSet.add(current);
			for (Integer neighbor: graph.outEdges(current)) {
				Integer tentativeGScore = gScore[current] + graph.getWeight(current, neighbor);
				Integer tentativeFScore = tentativeGScore + heuristicCostEstimate(neighbor, goal);
				if (closedSet.contains(neighbor) && tentativeFScore >= fScore[neighbor]) {
					continue;
				}
				if (!openSet.contains(neighbor) || tentativeFScore < fScore[neighbor]) {
					pi[neighbor] = current;
					gScore[neighbor] = tentativeGScore;
					fScore[neighbor] = tentativeFScore;
					if (!openSet.contains(neighbor)) {
						openSet.add(neighbor);
					}
				}
			}
		}		
		return pi;
	}
	
	int heuristicCostEstimate(int start, int goal) {
		return 1; // TODO: 
	}
	
	Integer getLowestFScore(Set<Integer> openSet, Integer[] fScore) {
		Integer lowest = Integer.MAX_VALUE;
		for (Integer i: openSet) {
			if (lowest == Integer.MAX_VALUE || fScore[i] < fScore[lowest]) {
				lowest = i;
			}
		}
		return lowest;
	}
}
