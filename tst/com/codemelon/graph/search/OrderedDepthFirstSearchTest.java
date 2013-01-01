package com.codemelon.graph.search;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.types.OrderedDfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 29, 2012
 *
 */
public class OrderedDepthFirstSearchTest {
	private DiGraph<OrderedDfsVertex> graph;
	private static final int CIRCULAR_GRAPH_SIZE = 1000;

	@After
	public void tearDown() {
		graph = null;
	}
	/**
	 * Graph from CLRS, p. 605
	 * Test method for {@link com.codemelon.graph.util.OrderedDepthFirstSearch.InOrderDepthFirstSearch#search()}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<Character, OrderedDfsVertex> vertices = setUpSmallCLRSGraph();
		new OrderedDepthFirstSearch(graph).search();
		// all vertices are black
		for (char i = 'u'; i <= 'z'; i++) {
			assertEquals("Vertex is black", Color.BLACK, vertices.get(i).getColor());
			assertTrue("Discovery time is before finish time", vertices.get(i).getDiscoveryTime() 
					< vertices.get(i).getFinishTime());
		}
		assertEquals("Discovery time is correct", 0, vertices.get('u').getDiscoveryTime());
		assertEquals("Finish time is correct", 7, vertices.get('u').getFinishTime());
		assertEquals("Discovery time is correct", 1, vertices.get('v').getDiscoveryTime());
		assertEquals("Finish time is correct", 6, vertices.get('v').getFinishTime());
		assertEquals("Discovery time is correct", 8, vertices.get('w').getDiscoveryTime());
		assertEquals("Finish time is correct", 11, vertices.get('w').getFinishTime());
		assertEquals("Discovery time is correct", 3, vertices.get('x').getDiscoveryTime());
		assertEquals("Finish time is correct", 4, vertices.get('x').getFinishTime());
		assertEquals("Discovery time is correct", 2, vertices.get('y').getDiscoveryTime());
		assertEquals("Finish time is correct", 5, vertices.get('y').getFinishTime());
		assertEquals("Discovery time is correct", 9, vertices.get('z').getDiscoveryTime());
		assertEquals("Finish time is correct", 10, vertices.get('z').getFinishTime());
	}
	@Test
	public void testBiggerCircularGraph() {
		ArrayList<OrderedDfsVertex> vertices = setUpBiggerCircularGraph();
		new OrderedDepthFirstSearch(graph).search();	
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			assertEquals("Color is black", Color.BLACK, vertices.get(i).getColor());
			assertEquals("Correct discovery time", i, vertices.get(i).getDiscoveryTime());
			assertEquals("Correct finish time", 2 * CIRCULAR_GRAPH_SIZE - i - 1 - 
					DepthFirstSearch.FIRST_DISCOVERY_TIME, vertices.get(i).getFinishTime());
		}
	}
	/**
	 * Graph from CLRS, p. 605
	 */
	public HashMap<Character, OrderedDfsVertex> setUpSmallCLRSGraph() {
		HashMap<Character, OrderedDfsVertex> vertices = new HashMap<Character, OrderedDfsVertex>();
		for (char i = 'u'; i <= 'z'; i++) {
			//vertices.put(i, new OrderedDfsVertex());
			vertices.get(i).setSearchOrder(i);
		}
		graph = new DiGraph<OrderedDfsVertex>(vertices.values());
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
	
	public ArrayList<OrderedDfsVertex> setUpBiggerCircularGraph() {
		ArrayList<OrderedDfsVertex> vertices = new ArrayList<OrderedDfsVertex>(CIRCULAR_GRAPH_SIZE);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			// vertex label will be the same as index in the array
			//vertices.add(new OrderedDfsVertex());
			vertices.get(i).setSearchOrder(i);
		}
		graph = new DiGraph<OrderedDfsVertex>(vertices);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE - 1; i++) {
			graph.addEdge(vertices.get(i), vertices.get(i + 1));
		}
		// now close the circle
		graph.addEdge(vertices.get(CIRCULAR_GRAPH_SIZE - 1), vertices.get(0));
		return vertices;
	}
}
