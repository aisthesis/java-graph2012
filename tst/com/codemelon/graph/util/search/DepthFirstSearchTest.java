/**
 * 
 */
package com.codemelon.graph.util.search;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 26, 2012
 *
 */
public class DepthFirstSearchTest {
	private DiGraph graph;

	/**
	 * 
	 */
	@Before
	public void setUp() {
	}

	/**
	 * 
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.DepthFirstSearch#search()}.
	 */
	@Test
	public void testSearch() {
		HashMap<Character, Vertex> vertices = setUpSmallCLRSGraph();
		new DepthFirstSearch(graph).search();
		// all vertices are black
		for (char i = 'u'; i <= 'z'; i++) {
			assertEquals("Vertex is black", Color.BLACK, vertices.get(i).color);
			assertTrue("Discovery time is before finish time", vertices.get(i).discoveryTime 
					< vertices.get(i).finishTime);
		}
	}
	/**
	 * Graph from CLRS, p. 605
	 */
	public HashMap<Character, Vertex> setUpSmallCLRSGraph() {
		HashMap<Character, Vertex> vertices = new HashMap<Character, Vertex>();
		for (char i = 'u'; i <= 'z'; i++) {
			vertices.put(i, new Vertex(i));
		}
		graph = new DiGraph(vertices.values());
		graph.addEdge(vertices.get('u'), vertices.get('v'));
		graph.addEdge(vertices.get('u'), vertices.get('x'));
		graph.addEdge(vertices.get('v'), vertices.get('y'));
		graph.addEdge(vertices.get('w'), vertices.get('y'));
		graph.addEdge(vertices.get('w'), vertices.get('z'));
		graph.addEdge(vertices.get('x'), vertices.get('v'));
		graph.addEdge(vertices.get('y'), vertices.get('x'));
		graph.addEdge(vertices.get('z'), vertices.get('z'));
		return vertices;
	}
}
