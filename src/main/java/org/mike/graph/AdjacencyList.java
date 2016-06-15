package org.mike.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList implements Graph {
	List<EdgeNode>[] edges;
	// TODO: should keep indegree as well as outdegree for topo sorts?
	int[] degree;
	int nvertices;
	int nedges;
	boolean directed;
	
	AdjacencyList(int n, boolean directed) {
	    this.edges = new List[n];
	    this.degree = new int[n];
	    this.directed = directed;
	}
	
	@Override
	public void addEdge(int x, int y, int weight) {
	    add(x, y, weight);
	    if (!directed) {
	        add(y, x, weight);
	    }
	    nedges++;
	}
	
	void add(int x, int y, int weight) {
        EdgeNode p = new EdgeNode(y, weight);
        if (edges[x] == null) {
            edges[x] = new ArrayList<EdgeNode>();
        }
        edges[x].add(p);
        degree[x]++;
	}
	
	@Override
	public boolean hasEdge(int x, int y) {
	    for (EdgeNode p : edges[x])
	        if (p.y == y) return true;
	    return false;
	}

	// TODO: very inefficient when you could just return the list.
	//   yet very generic. could go with an EdgeNode impl with
	//   adj matrix as well.
	@Override
	public List<Integer> outEdges(int x) {
		List<Integer> outEdges = new ArrayList<Integer>();
		if (edges[x] != null) {
	        for (EdgeNode p : edges[x]) {
	            outEdges.add(p.y);
	        }
		}
		return outEdges;
	}

	@Override
	public List<EdgeNode> outEdges2(int x) {
	    return edges[x];
	}
	
	@Override
	public void removeEdge(int x, int y) {
	    if (edges[x] != null) {
	        EdgeNode toRemove = null;
	        for (EdgeNode p : edges[x]) {
	            if (p.y == y) {
	                toRemove = p;
	                break;
	            }
	        }
	        edges[x].remove(toRemove);
	    }
	}
	
	// TODO: inefficient, but..., maybe EdgeNode could track inEdges? How?
	//  keep two arrays, one for outedges, one for indedges?
	@Override
	public List<Integer> inEdges(int x) {
        List<Integer> inEdges = new ArrayList<Integer>();
	    for (int i = 0; i < edges.length; i++) {
	        if (edges[i] != null) {
	            for (EdgeNode p : edges[i]) {
	                if (p.y == x) {
	                    inEdges.add(i);
	                }
	            }
	        }
	    }
		return inEdges;
	}

	@Override
	public int size() {
	    return edges.length;
	}

	// TODO: very inefficient as well if you already have
	//   the edge node.
	@Override 
	public int getWeight(int x, int y) {
	    for (EdgeNode p : edges[x])
	        if (p.y == y) return p.weight;
	    return 0;
	}
	
	@Override
	public void clear() {
	}
}
