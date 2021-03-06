package org.mike.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix implements Graph {
	int size;
	int[][] matrix;
	// TODO: need directed/undirected

	AdjacencyMatrix(int n) {
		size = n;
		init();
	}
	
	void init() {
		matrix = new int[size][size];
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
    public List<EdgeNode> outEdges2(int i) {
        List<EdgeNode> edges = new ArrayList<>();
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[i][j] > 0) {
                EdgeNode p = new EdgeNode(j, matrix[i][j]);
                edges.add(p);
            }
        }
        return edges;
    }

	@Override
	public List<Integer> inEdges(int j) {
		List<Integer> edges = new ArrayList<Integer>();
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][j] > 0) {
				edges.add(i);
			}
		}
		return edges;
	}

	@Override
	public int getWeight(int i, int j) {
		return matrix[i][j];
	}
		
	@Override
	public void clear() {
		init();
	}
}
