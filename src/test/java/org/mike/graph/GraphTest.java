package org.mike.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GraphTest {
	Graph g;
	
	@Parameters
	public static Graph[] instances() {
		Graph[] gArray = {new AdjacencyList(10, true), new AdjacencyMatrix(10)};
		return gArray;
	}
	
	public GraphTest(Graph g) {
		this.g = g;
	}
	
	@Before
	public void before() 
			throws InstantiationException, IllegalAccessException {
		g.clear();
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

	@Test
	public void testOutEdges() {
		g.addEdge(0, 1, 1);
		g.addEdge(0, 2, 2);
		g.addEdge(0, 3, 3);
		g.addEdge(0, 4, 4);
		g.addEdge(0, 5, 5);
		g.addEdge(0, 6, 6);
		assertEquals(6, g.outEdges(0).size());
	}


	@Test
	public void testInEdges() {
		g.addEdge(1, 0, 1);
		g.addEdge(2, 0, 2);
		g.addEdge(3, 0, 3);
		g.addEdge(4, 0, 4);
		g.addEdge(5, 0, 5);
		g.addEdge(6, 0, 6);
		assertEquals(6, g.inEdges(0).size());
	}

	public void assertEdge(int i, int j, int w) {
		assertTrue(g.hasEdge(i, j));
		assertEquals(w, g.getWeight(i, j));
	}
}
