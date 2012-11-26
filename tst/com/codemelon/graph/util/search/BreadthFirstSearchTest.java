/**
 * 
 */
package com.codemelon.graph.util.search;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class BreadthFirstSearchTest {
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	private DiGraph graph;
	private ArrayList<Vertex> vertices;

	/**
	 * 
	 */
	@Before
	public void setUp() {
		vertices = new ArrayList<Vertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.add(new Vertex(i));
		}
		graph = new DiGraph(vertices);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH / 2; i++) {
			graph.addEdge(vertices.get(i), vertices.get(i + 1));
		}
		graph.addEdge(vertices.get(VERTICES_IN_TEST_GRAPH / 4), vertices.get(VERTICES_IN_TEST_GRAPH / 2 + 1));
	}

	/**
	 * 
	 */
	@After
	public void tearDown() {
		vertices = null;
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.BreadthFirstSearch#breadthFirstSearch(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testBreadthFirstSearch() {
		new BreadthFirstSearch(graph).breadthFirstSearch(vertices.get(1));
		// vertex 0 is unreachable and hence white, etc.
		assertEquals("Vertex 0 is white", Color.WHITE, vertices.get(0).color);
		assertEquals("Vertex 0 has distance -1", -1, vertices.get(0).distance);
		assertNull("Vertex 0 has null parent", vertices.get(0).parent);
		// vertices 1 through VERTICES_IN_TEST_GRAPH / 2 + 1 are black
		for (int i = 1; i < VERTICES_IN_TEST_GRAPH / 2 + 1; i++) {
			assertEquals("Reachable vertices are black", Color.BLACK, vertices.get(i).color);
		}
		// vertices have correct distance
		for (int i = 1; i < VERTICES_IN_TEST_GRAPH / 2; i++) {
			assertEquals("Correct distance for reachable vertices", i - 1, vertices.get(i).distance);
		}
		assertEquals("Correct distance for fork", VERTICES_IN_TEST_GRAPH / 4, 
				vertices.get(VERTICES_IN_TEST_GRAPH / 2 + 1).distance);
		for (int i = VERTICES_IN_TEST_GRAPH / 2 + 2; i < VERTICES_IN_TEST_GRAPH; i++) {
			assertEquals("Correct distances for unreachable vertices", -1, vertices.get(i).distance);
		}
		// vertices have correct parents
		assertNull("Source vertex has null parent", vertices.get(1).parent);
		for (int i = 2; i < VERTICES_IN_TEST_GRAPH / 2; i++) {
			assertEquals("Correct parents for reachable vertices", vertices.get(i - 1), 
					vertices.get(i).parent);
		}
		assertEquals("Correct parent for fork", vertices.get(VERTICES_IN_TEST_GRAPH / 4), 
				vertices.get(VERTICES_IN_TEST_GRAPH / 2 + 1).parent);
		for (int i = VERTICES_IN_TEST_GRAPH / 2 + 2; i < VERTICES_IN_TEST_GRAPH; i++) {
			assertNull("Null parent for unreachable vertices", vertices.get(i).parent);
		}
	}
}
