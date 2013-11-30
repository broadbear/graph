package org.mike.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix implements Graph {
	Integer[][] matrix;

	AdjacencyMatrix(int n) {
		matrix = new Integer[n][n];
	}
	
	@Override
	public int size() {
		return matrix.length;
	}

	@Override
	public void addEdge(int i, int j, int weight) {
		matrix[i][j] = weight;
	}

	@Override
	public void removeEdge(int i, int j) {
		matrix[i][j] = 0;
	}

	@Override
	public boolean hasEdge(int i, int j) {
		return matrix[i][j] > 0;
	}

	@Override
	public List<Integer> outEdges(int i) {
		List<Integer> edges = new ArrayList<Integer>();
		for (int j = 0; j < matrix.length; j++) {
			if (matrix[i][j] > 0) {
				edges.add(j);
			}
		}
		return edges;
	}

	@Override
	public List<Integer> inEdges(int i) {
		List<Integer> edges = new ArrayList<Integer>();
		// TODO: tbd
		return edges;
	}

	@Override
	public int getWeight(int i, int j) {
		return matrix[i][j];
	}
}