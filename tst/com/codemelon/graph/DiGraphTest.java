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
public class DiGraphTest {
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	private DiGraph graph;
	private ArrayList<Vertex> vertices;

	/**
	 */
	@Before
	public void setUp() {
		vertices = new ArrayList<Vertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.add(new Vertex());
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		graph = null;
		vertices = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.DiGraph#DiGraph()}.
	 */
	@Test
	public void testDiGraph() {
		graph = new DiGraph();
		assertEquals("Graph is initialized with no vertices", 0, graph.vertices());
	}

	/**
	 * Test method for {@link com.codemelon.graph.DiGraph#DiGraph(java.util.Collection)}.
	 */
	@Test
	public void testDiGraphCollectionOfVertex() {
		graph = new DiGraph(vertices);
		assertEquals("Graph contains same number of vertices as collection used for initialization",
				VERTICES_IN_TEST_GRAPH, graph.vertices());
	}

	/**
	 * Test method for {@link com.codemelon.graph.DiGraph#addVertex(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddVertex() {
		graph = new DiGraph();
		Vertex v = new Vertex();
		graph.addVertex(v);
		assertEquals("Correct vertex count after adding a vertex to empty graph", 1, graph.vertices());
	}

	/**
	 * Test method for {@link com.codemelon.graph.DiGraph#addEdge(com.codemelon.graph.vertex.Vertex, com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddEdge() {
		graph = new DiGraph(vertices);
		graph.addEdge(vertices.get(0), vertices.get(1));
		assertEquals("Correct edge count after adding a single edge", 1L, graph.edges());
	}
	
	/**
	 * Test adding an edge when the source vertex is not present in the graph
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddEdgeSourceVertexNotPresent() {
		graph = new DiGraph(vertices);
		graph.addEdge(new Vertex(), vertices.get(1));		
	}
	
	/**
	 * Test adding an edge when the target vertex is not present in the graph
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddEdgeTargetVertexNotPresent() {
		graph = new DiGraph(vertices);
		graph.addEdge(vertices.get(0), new Vertex());		
	}

	/**
	 * Test method for {@link com.codemelon.graph.DiGraph#removeEdge(com.codemelon.graph.vertex.Vertex, com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testRemoveEdge() {
		graph = new DiGraph(vertices);
		graph.addEdge(vertices.get(0), vertices.get(1));
		graph.addEdge(vertices.get(1), vertices.get(2));
		assertTrue("Correct value returned when edge is present", 
				graph.removeEdge(vertices.get(0), vertices.get(1)));
		assertFalse("Correct value returned when edge is not present",
				graph.removeEdge(vertices.get(2), vertices.get(3)));
		assertEquals("Correct number of edges remaining", 1, graph.edges());
		assertFalse("Removed edge not present", graph.hasEdge(vertices.get(0), vertices.get(1)));
		assertTrue("Unremoved edge present", graph.hasEdge(vertices.get(1), vertices.get(2)));
	}

	/**
	 * Test method for {@link com.codemelon.graph.DiGraph#vertices()}.
	 */
	@Test
	public void testVertices() {
		graph = new DiGraph(vertices);
		assertEquals("Graph contains same number of vertices as collection used for initialization",
				VERTICES_IN_TEST_GRAPH, graph.vertices());
	}

	/**
	 * Test method for {@link com.codemelon.graph.DiGraph#edges()}.
	 */
	@Test
	public void testEdges() {
		graph = new DiGraph(vertices);
		// connect all vertices
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			for (int j = 0; j < VERTICES_IN_TEST_GRAPH; j++) {
				graph.addEdge(vertices.get(i), vertices.get(j));
			}
		}
		assertEquals("Correct number of edges after inserting all possible edges",
				VERTICES_IN_TEST_GRAPH * VERTICES_IN_TEST_GRAPH, graph.edges());
	}
}
