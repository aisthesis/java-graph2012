/**
 * 
 */
package com.codemelon.graph.util.search.depthfirst;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
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
	private static final int CIRCULAR_GRAPH_SIZE = 1000;

	@After
	public void tearDown() {
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.DepthFirstSearch#search()}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<Character, Vertex> vertices = setUpSmallCLRSGraph();
		new DepthFirstSearch(graph).search();
		// all vertices are black
		for (char i = 'u'; i <= 'z'; i++) {
			assertEquals("Vertex is black", Color.BLACK, vertices.get(i).color);
			assertTrue("Discovery time is before finish time", vertices.get(i).discoveryTime 
					< vertices.get(i).finishTime);
		}
	}
	@Test
	public void testBiggerCircularGraph() {
		ArrayList<Vertex> vertices = setUpBiggerCircularGraph();
		new DepthFirstSearch(graph).search();		
		//vertex first discovered will be vertex last finished in this case
		int indexOfFirstDiscovery = -1;
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			if (vertices.get(i).discoveryTime == 1) {
				indexOfFirstDiscovery = i;
				break;
			}
		}
		assertEquals("Vertex first discovered has last finish time", 
				CIRCULAR_GRAPH_SIZE * 2, vertices.get(indexOfFirstDiscovery).finishTime);
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
	
	public ArrayList<Vertex> setUpBiggerCircularGraph() {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>(CIRCULAR_GRAPH_SIZE);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			// vertex label will be the same as index in the array
			vertices.add(new Vertex(i));
		}
		graph = new DiGraph(vertices);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE - 1; i++) {
			graph.addEdge(vertices.get(i), vertices.get(i + 1));
		}
		// now close the circle
		graph.addEdge(vertices.get(CIRCULAR_GRAPH_SIZE - 1), vertices.get(0));
		return vertices;
	}
}
