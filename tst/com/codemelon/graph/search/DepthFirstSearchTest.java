/**
 * 
 */
package com.codemelon.graph.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;
import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.types.DfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 26, 2012
 *
 */
public class DepthFirstSearchTest {
	private DiGraph<DfsVertex> graph;
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
		HashMap<Character, DfsVertex> vertices = setUpSmallCLRSGraph();
		assertFalse("Graph contains a cycle", new DepthFirstSearch(graph).search());
		// all vertices are black
		for (char i = 'u'; i <= 'z'; i++) {
			assertEquals("Vertex is black", Color.BLACK, vertices.get(i).getColor());
			assertTrue("Discovery time is before finish time", vertices.get(i).getDiscoveryTime() 
					< vertices.get(i).getFinishTime());
		}
	}
	@Test
	public void testBiggerCircularGraph() {
		ArrayList<DfsVertex> vertices = setUpBiggerCircularGraph();
		assertFalse("Graph is not acyclic", new DepthFirstSearch(graph).search());		
		//vertex first discovered will be vertex last finished in this case
		int indexOfFirstDiscovery = -1;
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			if (vertices.get(i).getDiscoveryTime() == DepthFirstSearch.FIRST_DISCOVERY_TIME) {
				indexOfFirstDiscovery = i;
			}
			else if (i > 0) {
				assertEquals("All but one edge is a tree edge", EdgeType.TREE,
						vertices.get(i - 1).getEdgeType(vertices.get(i)));
			}
		}
		assertEquals("Vertex first discovered has last finish time", 
				CIRCULAR_GRAPH_SIZE * 2 - 1 + DepthFirstSearch.FIRST_DISCOVERY_TIME, 
				vertices.get(indexOfFirstDiscovery).getFinishTime());
		int indexOfLastDiscovery = (indexOfFirstDiscovery == 
				0 ? CIRCULAR_GRAPH_SIZE - 1 : indexOfFirstDiscovery - 1);
		assertEquals("Edge from last to first is a back edge", EdgeType.BACK,
				vertices.get(indexOfLastDiscovery).getEdgeType(vertices.get(indexOfFirstDiscovery)));
	}
	private ArrayList<DfsVertex> setUpBiggerCircularGraph() {
		ArrayList<DfsVertex> vertices = new ArrayList<DfsVertex>(CIRCULAR_GRAPH_SIZE);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE; i++) {
			// vertex label will be the same as index in the array
			vertices.add(new DfsVertex());
		}
		graph = new DiGraph<DfsVertex>(vertices);
		for (int i = 0; i < CIRCULAR_GRAPH_SIZE - 1; i++) {
			graph.addEdge(vertices.get(i), vertices.get(i + 1));
		}
		// now close the circle
		graph.addEdge(vertices.get(CIRCULAR_GRAPH_SIZE - 1), vertices.get(0));
		return vertices;
	}
	
	/**
	 * Graph from CLRS, p. 605
	 */
	private HashMap<Character, DfsVertex> setUpSmallCLRSGraph() {
		HashMap<Character, DfsVertex> vertices = new HashMap<Character, DfsVertex>();
		for (char i = 'u'; i <= 'z'; i++) {
			vertices.put(i, new DfsVertex());
		}
		graph = new DiGraph<DfsVertex>(vertices.values());
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
