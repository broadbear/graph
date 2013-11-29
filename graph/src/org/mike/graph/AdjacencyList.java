package org.mike.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList implements Graph {

	List<Vertex> vertexList;
	
	AdjacencyList(int n) {
		vertexList = new ArrayList<Vertex>();
		for (int i = 0; i < n; i++) {
			vertexList.add(new Vertex());
		}
	}
	
	@Override
	public void addEdge(int i, int j, int weight) {
		Vertex u = vertexList.get(i);
		Vertex v = vertexList.get(j);
		Edge uv = new Edge(v, weight);
		u.addEdge(uv);
	}
	
	@Override
	public boolean hasEdge(int i, int j) {
		Vertex u = vertexList.get(i);
		Vertex v = vertexList.get(j);
		for (Edge uv: u.outEdges()) {
			if (uv.out == v) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<Integer> outEdges(int i) { //TODO: maybe vertex should contain an 'index' value
		Vertex v = vertexList.get(i);
		List<Integer> outEdges = new ArrayList<Integer>();
		for (Edge uv: v.outEdges()) {
			outEdges.add(vertexList.indexOf(uv.out));
		}
		return outEdges;
	}
	
	@Override
	public void removeEdge(int i, int j) {
		
	}
	
	@Override
	public List<Integer> inEdges(int i) {
		return null;
	}

	@Override
	public int size() {
		return vertexList.size();
	}

	@Override 
	public int getWeight(int i, int j) {
		Vertex u = vertexList.get(i);
		Vertex v = vertexList.get(j);
		for (Edge uv: u.outEdges()) {
			if (uv.out == v) {
				return uv.weight;
			}
		}
		return 0;
	}
	
	static class Vertex {
		enum Color {
			RED,
			BLACK
		}
		Color color;
		int distance;
		List<Edge> edgeList = new ArrayList<Edge>();
		List<Edge> outEdges() {
			return edgeList;
		}
		void addEdge(Edge e) {
			edgeList.add(e);
		}
	}
	
	static class Edge {
		int weight;
		Vertex out;
		Edge(Vertex out, int weight) {
			this.out = out;
			this.weight = weight;
		}
	}
}
