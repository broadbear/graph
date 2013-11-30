package org.mike.graph;

import java.util.List;

public class AdjacencyMatrix implements Graph {
	Integer[][] matrix;

	AdjacencyMatrix(int n) {
		matrix = new Integer[n][n];
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addEdge(int i, int j, int weight) {
		matrix[i][j] = weight;
	}

	@Override
	public void removeEdge(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasEdge(int i, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> outEdges(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> inEdges(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWeight(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}
}
