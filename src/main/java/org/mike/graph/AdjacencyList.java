package org.mike.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList implements Graph {

	List<Vertex> vertexList;
	
	AdjacencyList(int n) {
		vertexList = new ArrayList<Vertex>();
		for (int i = 0; i < n; i++) {
			vertexList.add(new Vertex(n));
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
	public List<Integer> outEdges(int i) {
		Vertex v = vertexList.get(i);
		List<Integer> outEdges = new ArrayList<Integer>();
		for (Edge uv: v.outEdges()) {
			outEdges.add(uv.out.getIndex());
		}
		return outEdges;
	}
	
	@Override
	public void removeEdge(int i, int j) {
		
	}
	
	@Override
	public List<Integer> inEdges(int i) {
		Vertex v = vertexList.get(i);
		List<Integer> inEdges = new ArrayList<Integer>();
		for (int n = 0; n < vertexList.size(); n++) {
			Vertex u = vertexList.get(n);
			for (Edge uv: u.outEdges()) {
				if (uv.out == v) {
					inEdges.add(uv.out.getIndex());
				}
			}
		}
		return inEdges;
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
		int index;
		enum Color {
			RED,
			BLACK
		}
		Color color;
		int distance;
		List<Edge> edgeList = new ArrayList<Edge>();
		Vertex(int index) {
			this.index = index;
		}
		int getIndex() {
			return index;
		}
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
