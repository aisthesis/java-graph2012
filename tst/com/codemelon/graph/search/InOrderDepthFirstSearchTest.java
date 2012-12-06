/**
 * 
 */
package com.codemelon.graph.search;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.vertex.LabelComparator;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Nov 29, 2012
 *
 */
public class InOrderDepthFirstSearchTest {
	private DiGraph graph;
	private static final int CIRCULAR_GRAPH_SIZE = 1000;

	@After
	public void tearDown() {
		graph = null;
	}
	/**
	 * Graph from CLRS, p. 605
	 * Test method for {@link com.codemelon.graph.util.search.InOrderDepthFirstSearch#search()}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<Character, CompleteVertex> vertices = setUpSmallCLRSGraph();
		new InOrderDepthFirstSearch(graph, new LabelComparator()).search();
		// all vertices are black
		for (char i = 'u'; i <= 'z'; i++) {
			assertEquals("Vertex is black", Color.BLACK, vertices.get(i).color);
			assertTrue("Discovery time is before finish time", vertices.get(i).discoveryTime 
					< vertices.get(i).finishTime);
		}
		assertEquals("Discovery time is correct", 1, vertices.get('u').discoveryTime);
		assertEquals("Finish time is correct", 8, vertices.get('u').finishTime);
		assertEquals("Discovery time is correct", 2, vertices.get('v').discoveryTime);
		assertEquals("Finish time is correct", 7, vertices.get('v').finishTime);
		assertEquals("Discovery time is correct", 9, vertices.get('w').discoveryTime);
		assertEquals("Finish time is correct", 12, vertices.get('w').finishTime);
		assertEquals("Discovery time is correct", 4, vertices.get('x').discoveryTime);
		assertEquals("Finish time is correct", 5, vertices.get('x').finishTime);
		assertEquals("Discovery time is correct", 3, vertices.get('y').discoveryTime);
		assertEquals("Finish time is correct", 6, vertices.get('y').finishTime);
		assertEquals("Discovery time is correct", 10, vertices.get('z').discoveryTime);
		assertEquals("Finish time is correct", 11, vertices.get('z').finishTime);
	}
	@Test
	public void testBiggerCircularGraph() {
		ArrayList<CompleteVertex> vertices = setUpBiggerCircularGraph();
		new InOrderDepthFirstSearch(graph, new LabelComparator()).search();	
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			assertEquals("Color is black", Color.BLACK, vertices.get(i).color);
			assertEquals("Correct discovery time", i + 1, vertices.get(i).discoveryTime);
			assertEquals("Correct finish time", 2 * CIRCULAR_GRAPH_SIZE - i, 
					vertices.get(i).finishTime);
		}
	}
	/**
	 * Graph from CLRS, p. 605
	 */
	public HashMap<Character, CompleteVertex> setUpSmallCLRSGraph() {
		HashMap<Character, CompleteVertex> vertices = new HashMap<Character, CompleteVertex>();
		for (char i = 'u'; i <= 'z'; i++) {
			vertices.put(i, new CompleteVertex(i));
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
	
	public ArrayList<CompleteVertex> setUpBiggerCircularGraph() {
		ArrayList<CompleteVertex> vertices = new ArrayList<CompleteVertex>(CIRCULAR_GRAPH_SIZE);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			// vertex label will be the same as index in the array
			vertices.add(new CompleteVertex(i));
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
