/**
 * 
 */
package com.codemelon.graph.util.search;

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.Graph;
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

	/**
	 * Graph with sequential (from vertex i to i + 1) edges on the lower-numbered vertices,
	 * no edges on higher-numbered vertices, and one forked edge.
	 */
	public ConcurrentHashMap<Integer, Vertex> setUpBigSparseGraph() {
		ConcurrentHashMap<Integer, Vertex> vertices = new ConcurrentHashMap<Integer, Vertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.put(i, new Vertex(i));
		}
		graph = new DiGraph(vertices.values());
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH / 2; i++) {
			graph.addEdge(vertices.get(i), vertices.get(i + 1));
		}
		graph.addEdge(vertices.get(VERTICES_IN_TEST_GRAPH / 4), vertices.get(VERTICES_IN_TEST_GRAPH / 2 + 1));
		return vertices;
	}
	
	/**
	 * Graph from CLRS, p. 596
	 */
	public ConcurrentHashMap<Character, Vertex> setUpSmallCLRSGraph() {
		ConcurrentHashMap<Character, Vertex> vertices = new ConcurrentHashMap<Character, Vertex>();
		for (char i = 'r'; i <= 'y'; i++) {
			vertices.put(i, new Vertex(i));
		}
		graph = new Graph(vertices.values());
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

	/**
	 * 
	 */
	@After
	public void tearDown() {
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.BreadthFirstSearch#search(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testBigSparseGraph() {
		ConcurrentHashMap<Integer, Vertex> vertices = setUpBigSparseGraph();
		new BreadthFirstSearch(graph).search(vertices.get(1));
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
	/**
	 * Graph from CLRS, p. 596
	 * Test method for {@link com.codemelon.graph.util.search.BreadthFirstSearch#search(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		ConcurrentHashMap<Character, Vertex> vertices = setUpSmallCLRSGraph();
		new BreadthFirstSearch(graph).search(vertices.get('s'));
		for (char i = 'r'; i <= 'y'; i++) {
			assertEquals("All vertices are black", Color.BLACK, vertices.get(i).color);
		}
		// each vertex has correct parent and distance
		assertEquals("Vertex r has correct distance", 1, vertices.get('r').distance);
		assertEquals("Vertex r has correct parent", vertices.get('s'), vertices.get('r').parent);
		assertEquals("Vertex s has correct distance", 0, vertices.get('s').distance);
		assertEquals("Vertex s has correct parent", null, vertices.get('s').parent);
		assertEquals("Vertex t has correct distance", 2, vertices.get('t').distance);
		assertEquals("Vertex t has correct parent", vertices.get('w'), vertices.get('t').parent);
		assertEquals("Vertex u has correct distance", 3, vertices.get('u').distance);
		// parent for u can be either t or x
		assertEquals("Vertex v has correct distance", 2, vertices.get('v').distance);
		assertEquals("Vertex v has correct parent", vertices.get('r'), vertices.get('v').parent);
		assertEquals("Vertex w has correct distance", 1, vertices.get('w').distance);
		assertEquals("Vertex w has correct parent", vertices.get('s'), vertices.get('w').parent);
		assertEquals("Vertex x has correct distance", 2, vertices.get('x').distance);
		assertEquals("Vertex x has correct parent", vertices.get('w'), vertices.get('x').parent);
		assertEquals("Vertex y has correct distance", 3, vertices.get('y').distance);
		assertEquals("Vertex y has correct parent", vertices.get('x'), vertices.get('y').parent);
	}
}
