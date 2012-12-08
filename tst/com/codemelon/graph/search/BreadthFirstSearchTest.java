package com.codemelon.graph.search;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.graph.Graph;
import com.codemelon.graph.vertex.types.BfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class BreadthFirstSearchTest {
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	private Graph<BfsVertex> graph;
	
	@After
	public void tearDown() {
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.BreadthFirstSearch#search(com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test
	public void testBigSparseGraph() {
		HashMap<Integer, BfsVertex> vertices = setUpBigSparseGraph();
		new BreadthFirstSearch(graph).search(vertices.get(1));
		// vertex 0 is unreachable and hence white, etc.
		assertEquals("Vertex 0 is white", Color.WHITE, vertices.get(0).getColor());
		assertEquals("Vertex 0 has distance -1", -1, vertices.get(0).getDistance());
		assertNull("Vertex 0 has null parent", vertices.get(0).getParent());
		// vertices 1 through VERTICES_IN_TEST_GRAPH / 2 + 1 are black
		for (int i = 1; i < VERTICES_IN_TEST_GRAPH / 2 + 1; i++) {
			assertEquals("Reachable vertices are black", Color.BLACK, vertices.get(i).getColor());
		}
		// vertices have correct distance
		for (int i = 1; i < VERTICES_IN_TEST_GRAPH / 2; i++) {
			assertEquals("Correct distance for reachable vertices", i - 1, vertices.get(i).getDistance());
		}
		assertEquals("Correct distance for fork", VERTICES_IN_TEST_GRAPH / 4, 
				vertices.get(VERTICES_IN_TEST_GRAPH / 2 + 1).getDistance());
		for (int i = VERTICES_IN_TEST_GRAPH / 2 + 2; i < VERTICES_IN_TEST_GRAPH; i++) {
			assertEquals("Correct distances for unreachable vertices", -1, vertices.get(i).getDistance());
		}
		// vertices have correct parents
		assertNull("Source vertex has null parent", vertices.get(1).getParent());
		for (int i = 2; i < VERTICES_IN_TEST_GRAPH / 2; i++) {
			assertEquals("Correct parents for reachable vertices", vertices.get(i - 1), 
					vertices.get(i).getParent());
		}
		assertEquals("Correct parent for fork", vertices.get(VERTICES_IN_TEST_GRAPH / 4), 
				vertices.get(VERTICES_IN_TEST_GRAPH / 2 + 1).getParent());
		for (int i = VERTICES_IN_TEST_GRAPH / 2 + 2; i < VERTICES_IN_TEST_GRAPH; i++) {
			assertNull("Null parent for unreachable vertices", vertices.get(i).getParent());
		}
	}
	/**
	 * Graph from CLRS, p. 596
	 * Test method for {@link com.codemelon.graph.util.search.BreadthFirstSearch#search(com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<Character, BfsVertex> vertices = setUpSmallCLRSGraph();
		new BreadthFirstSearch(graph).search(vertices.get('s'));
		for (char i = 'r'; i <= 'y'; i++) {
			assertEquals("All vertices are black", Color.BLACK, vertices.get(i).getColor());
		}
		// each vertex has correct parent and distance
		assertEquals("Vertex r has correct distance", 1, vertices.get('r').getDistance());
		assertEquals("Vertex r has correct parent", vertices.get('s'), vertices.get('r').getParent());
		assertEquals("Vertex s has correct distance", 0, vertices.get('s').getDistance());
		assertEquals("Vertex s has correct parent", null, vertices.get('s').getParent());
		assertEquals("Vertex t has correct distance", 2, vertices.get('t').getDistance());
		assertEquals("Vertex t has correct parent", vertices.get('w'), vertices.get('t').getParent());
		assertEquals("Vertex u has correct distance", 3, vertices.get('u').getDistance());
		// parent for u can be either t or x
		assertEquals("Vertex v has correct distance", 2, vertices.get('v').getDistance());
		assertEquals("Vertex v has correct parent", vertices.get('r'), vertices.get('v').getParent());
		assertEquals("Vertex w has correct distance", 1, vertices.get('w').getDistance());
		assertEquals("Vertex w has correct parent", vertices.get('s'), vertices.get('w').getParent());
		assertEquals("Vertex x has correct distance", 2, vertices.get('x').getDistance());
		assertEquals("Vertex x has correct parent", vertices.get('w'), vertices.get('x').getParent());
		assertEquals("Vertex y has correct distance", 3, vertices.get('y').getDistance());
		assertEquals("Vertex y has correct parent", vertices.get('x'), vertices.get('y').getParent());
	}
	
	/**
	 * throw an exception if path() called before search()
	 * Test method for {@link com.codemelon.graph.util.search.BreadthFirstSearch#path(com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test(expected=IllegalStateException.class)
	public void testPathBeforeSearch() {
		HashMap<Character, BfsVertex> vertices = setUpSmallCLRSGraph();
		new BreadthFirstSearch(graph).path(vertices.get('t'));
	}

	/**
	 * Graph with sequential (from vertex i to i + 1) edges on the lower-numbered vertices,
	 * no edges on higher-numbered vertices, and one forked edge.
	 */
	private HashMap<Integer, BfsVertex> setUpBigSparseGraph() {
		HashMap<Integer, BfsVertex> vertices = new HashMap<Integer, BfsVertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.put(i, new BfsVertex());
		}
		graph = new Graph<BfsVertex>(vertices.values());
		for (int i = 1; i < VERTICES_IN_TEST_GRAPH / 2; i++) {
			graph.addEdge(vertices.get(i), vertices.get(i + 1));
		}
		graph.addEdge(vertices.get(VERTICES_IN_TEST_GRAPH / 4), vertices.get(VERTICES_IN_TEST_GRAPH / 2 + 1));
		return vertices;
	}
	
	/**
	 * Graph from CLRS, p. 596
	 */
	private HashMap<Character, BfsVertex> setUpSmallCLRSGraph() {
		HashMap<Character, BfsVertex> vertices = new HashMap<Character, BfsVertex>();
		for (char i = 'r'; i <= 'y'; i++) {
			vertices.put(i, new BfsVertex());
		}
		graph = new Graph<BfsVertex>(vertices.values());
		graph.addEdge(vertices.get('r'), vertices.get('s'));
		graph.addEdge(vertices.get('r'), vertices.get('v'));
		graph.addEdge(vertices.get('s'), vertices.get('w'));
		graph.addEdge(vertices.get('t'), vertices.get('u'));
		graph.addEdge(vertices.get('t'), vertices.get('w'));
		graph.addEdge(vertices.get('t'), vertices.get('x'));
		graph.addEdge(vertices.get('u'), vertices.get('x'));
		graph.addEdge(vertices.get('u'), vertices.get('y'));
		graph.addEdge(vertices.get('w'), vertices.get('x'));
		graph.addEdge(vertices.get('x'), vertices.get('y'));
		return vertices;
	}
}
