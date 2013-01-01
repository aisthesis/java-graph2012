/**
 * 
 */
package com.codemelon.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.edge.WeightedEdge;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Nov 24, 2012
 *
 */
public class GraphTest {
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	private static final int EDGES_IN_DENSE_GRAPH = VERTICES_IN_TEST_GRAPH 
			* (VERTICES_IN_TEST_GRAPH - 1) / 2;
	private Graph graph;
	private ArrayList<CompleteVertex> vertices;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vertices = new ArrayList<CompleteVertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.add(new CompleteVertex());
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
	 * Test method for {@link com.codemelon.graph.Graph#addEdge(com.codemelon.graph.vertex.CompleteVertex, com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test
	public void testAddEdge() {
		assertTrue("Edge can be added", graph.addEdge(vertices.get(0), vertices.get(1)));
		assertTrue("Graph contains edge in opposite direction", 
				graph.containsEdge(vertices.get(1), vertices.get(0)));
	}

	/**
	 * Test method for {@link com.codemelon.graph.Graph#removeEdge(com.codemelon.graph.vertex.CompleteVertex, com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test
	public void testRemoveEdge() {
		assertTrue("Edge can be added", graph.addEdge(vertices.get(0), vertices.get(1)));
		assertTrue("Reverse edge is present and can be removed", 
				graph.removeEdge(vertices.get(1), vertices.get(0)));
		assertFalse("Original edge is no longer in graph", 
				graph.containsEdge(vertices.get(0), vertices.get(1)));
	}

	/**
	 * Test method for {@link com.codemelon.graph.Graph#edgeCount()}.
	 */
	@Test
	public void testEdges() {
		setUpDenseGraph();
		assertEquals("Correct number of edges after inserting all possible edges",
				EDGES_IN_DENSE_GRAPH, graph.edgeCount());
	}
	/**
	 * Test method for {@link com.codemelon.graph.Graph#getWeightedEdges()}.
	 */
	@Test
	public void testGetWeightedEdges() {
		setUpDenseGraph();
		Set<WeightedEdge> weightedEdges = graph.getWeightedEdges();
		assertEquals("Correct number of weighted edges in set",
				EDGES_IN_DENSE_GRAPH, weightedEdges.size());
	}
	
	private void setUpDenseGraph() {
		// connect all vertices
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				graph.addEdge(vertices.get(i), vertices.get(j));
			}
		}		
	}
}
