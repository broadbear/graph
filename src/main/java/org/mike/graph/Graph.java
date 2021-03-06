package org.mike.graph;

import java.util.List;

public interface Graph {
	int size();
	void addEdge(int i, int j, int weight);
	void removeEdge(int i, int j);
	boolean hasEdge(int i, int j);
	List<Integer> outEdges(int i);
	List<EdgeNode> outEdges2(int i);
	List<Integer> inEdges(int i);
	int getWeight(int i, int j);
	void clear();
	
    static class EdgeNode {
        int y;
        int weight;
        EdgeNode(int y, int weight) {
            this.y = y;
            this.weight = weight;
        }
    }
}
