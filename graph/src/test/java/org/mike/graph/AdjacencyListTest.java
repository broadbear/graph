package org.mike.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AdjacencyListTest {
	Graph g;
	
	@Before
	public void before() {
		g = new AdjacencyList(10);
	}
	
	@Test
	public void testAddEdge() {
		g.addEdge(0, 1, 5);
		assertEdge(0, 1, 5);
		assertEquals(0, g.inEdges(0).size());
		assertEquals(1, g.inEdges(1).size());
		assertEquals(0, g.outEdges(1).size());
		assertEquals(1, g.outEdges(0).size());
	}

	@Test
	public void testAddEdges() {
		g.addEdge(0, 1, 5);
		g.addEdge(0, 2, 6);
		assertEdge(0, 1, 5);
		assertEdge(0, 2, 6);
		assertEquals(0, g.inEdges(0).size());
		assertEquals(1, g.inEdges(1).size());
		assertEquals(1, g.inEdges(2).size());
		assertEquals(2, g.outEdges(0).size());
		assertEquals(0, g.outEdges(1).size());
		assertEquals(0, g.outEdges(2).size());
	}

	public void assertEdge(int i, int j, int w) {
		assertTrue(g.hasEdge(i, j));
		assertEquals(w, g.getWeight(i, j));
	}
}
