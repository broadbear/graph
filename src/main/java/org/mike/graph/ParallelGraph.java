package org.mike.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ParallelGraph {
	Graph graph;
	int P;
	boolean halt = false;
	int waiting;
	Queue<Integer> queue = new LinkedList<Integer>();
	Integer[] distance;
	
	ParallelGraph(Graph graph, int P) {
		this.graph = graph;
		this.P = P;
		distance = new Integer[graph.size()];
	}
	
	Integer[] mooresShortestPath(int s) {
		distance[s] = 0;
		for (int i = 0; i < graph.size(); i++) {
			if (i != s) {
				distance[i] = Integer.MAX_VALUE;
			}
		}
		queue.add(s);
		halt = false;
		waiting = 0;
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < P; i++) {
			final int finalI = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					while(!halt) {
						search(finalI);
					}
				}
			});
			t.start();
			threads.add(t);
		}
		for (Thread t: threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return distance;
	}

	void search(int i) {
		Integer u = dequeue();
		if (u != null) {
			for (Integer v: graph.outEdges(u)) {
				int newDistance = distance[u] + graph.getWeight(u, v);
				synchronized(v) {
					if (newDistance < distance[v]) {
						distance[v] = newDistance;
						if (!queueContains(v)) {
							enqueue(v);
						}
					}
				}
			}
		}
	}
	
	synchronized boolean queueContains(Integer v) {
		return queue.contains(v);
	}
	
	synchronized void enqueue(Integer v) {
		queue.add(v);
		notifyAll();
	}
		
	synchronized Integer dequeue() {
		try {
			Integer v = queue.remove();
			return v;
		}
		catch (NoSuchElementException e) {
			if (++waiting == P) {
				halt = true;
				notifyAll();
			}
			else {
				try {
					wait();
				}
				catch (InterruptedException ie) {
				}
				--waiting;
			}
			Integer v = null;
			return v;
		}
	}
}
