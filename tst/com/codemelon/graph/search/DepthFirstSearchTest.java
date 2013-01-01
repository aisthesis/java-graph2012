/**
 * 
 */
package com.codemelon.graph.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.CompleteVertex;
import com.codemelon.graph.vertex.types.DfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 26, 2012
 *
 */
public class DepthFirstSearchTest {
	private DiGraph<DfsVertex> graph;
	private static final int CIRCULAR_GRAPH_SIZE = 1000;
	

	private OldDiGraph oldGraph;

	@After
	public void tearDown() {
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.DepthFirstSearch#search()}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<Character, CompleteVertex> vertices = setUpSmallCLRSGraph();
		assertFalse("Graph is not acyclic", new DepthFirstSearch(oldGraph).search());
		// all vertices are black
		for (char i = 'u'; i <= 'z'; i++) {
			assertEquals("Vertex is black", Color.BLACK, vertices.get(i).color);
			assertTrue("Discovery time is before finish time", vertices.get(i).discoveryTime 
					< vertices.get(i).finishTime);
		}
	}
	@Test
	public void testBiggerCircularGraph() {
		ArrayList<CompleteVertex> vertices = setUpBiggerCircularGraph();
		assertFalse("Graph is not acyclic", new DepthFirstSearch(oldGraph).search());		
		//vertex first discovered will be vertex last finished in this case
		int indexOfFirstDiscovery = -1;
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			if (vertices.get(i).discoveryTime == 1) {
				indexOfFirstDiscovery = i;
			}
			else if (i > 0) {
				assertEquals("Edge from last to first is a back edge", EdgeType.TREE,
						vertices.get(i - 1).getEdgeType(vertices.get(i)));
			}
		}
		assertEquals("Vertex first discovered has last finish time", 
				CIRCULAR_GRAPH_SIZE * 2, vertices.get(indexOfFirstDiscovery).finishTime);
		int indexOfLastDiscovery = (indexOfFirstDiscovery == 
				0 ? CIRCULAR_GRAPH_SIZE - 1 : indexOfFirstDiscovery - 1);
		assertEquals("Edge from last to first is a back edge", EdgeType.BACK,
				vertices.get(indexOfLastDiscovery).getEdgeType(vertices.get(indexOfFirstDiscovery)));
	}
	
	/**
	 * Graph from CLRS, p. 605
	 */
	private HashMap<Character, CompleteVertex> setUpSmallCLRSGraph() {
		HashMap<Character, CompleteVertex> vertices = new HashMap<Character, CompleteVertex>();
		for (char i = 'u'; i <= 'z'; i++) {
			vertices.put(i, new CompleteVertex(i));
		}
		oldGraph = new OldDiGraph(vertices.values());
		oldGraph.addEdge(vertices.get('u'), vertices.get('v'));
		oldGraph.addEdge(vertices.get('u'), vertices.get('x'));
		oldGraph.addEdge(vertices.get('v'), vertices.get('y'));
		oldGraph.addEdge(vertices.get('w'), vertices.get('y'));
		oldGraph.addEdge(vertices.get('w'), vertices.get('z'));
		oldGraph.addEdge(vertices.get('x'), vertices.get('v'));
		oldGraph.addEdge(vertices.get('y'), vertices.get('x'));
		oldGraph.addEdge(vertices.get('z'), vertices.get('z'));
		return vertices;
	}
	
	private ArrayList<CompleteVertex> setUpBiggerCircularGraph() {
		ArrayList<CompleteVertex> vertices = new ArrayList<CompleteVertex>(CIRCULAR_GRAPH_SIZE);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			// vertex label will be the same as index in the array
			vertices.add(new CompleteVertex(i));
		}
		oldGraph = new OldDiGraph(vertices);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE - 1; i++) {
			oldGraph.addEdge(vertices.get(i), vertices.get(i + 1));
		}
		// now close the circle
		oldGraph.addEdge(vertices.get(CIRCULAR_GRAPH_SIZE - 1), vertices.get(0));
		return vertices;
	}
}
