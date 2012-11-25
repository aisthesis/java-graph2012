/**
 * 
 */
package com.codemelon.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 24, 2012
 *
 */
public class GraphTest {
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	private Graph graph;
	private ArrayList<Vertex> vertices;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vertices = new ArrayList<Vertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.add(new Vertex());
		}
		graph = new Graph(vertices);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		vertices = null;
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.Graph#addEdge(com.codemelon.graph.vertex.Vertex, com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddEdge() {
		assertTrue("Edge can be added", graph.addEdge(vertices.get(0), vertices.get(1)));
		assertTrue("Graph contains edge in opposite direction", 
				graph.hasEdge(vertices.get(1), vertices.get(0)));
	}

	/**
	 * Test method for {@link com.codemelon.graph.Graph#removeEdge(com.codemelon.graph.vertex.Vertex, com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testRemoveEdge() {
		assertTrue("Edge can be added", graph.addEdge(vertices.get(0), vertices.get(1)));
		assertTrue("Reverse edge is present and can be removed", 
				graph.removeEdge(vertices.get(1), vertices.get(0)));
		assertFalse("Original edge is no longer in graph", 
				graph.hasEdge(vertices.get(0), vertices.get(1)));
	}

	/**
	 * Test method for {@link com.codemelon.graph.Graph#edges()}.
	 */
	@Test
	public void testEdges() {
		final long EXPECTED_RESULT = VERTICES_IN_TEST_GRAPH * (VERTICES_IN_TEST_GRAPH - 1) / 2;
		// connect all vertices
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				graph.addEdge(vertices.get(i), vertices.get(j));
			}
		}
		assertEquals("Correct number of edges after inserting all possible edges",
				EXPECTED_RESULT, graph.edges());
	}

}
