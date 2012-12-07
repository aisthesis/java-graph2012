/**
 * 
 */
package com.codemelon.graph.search;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.OldDiGraph;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class TopologicalSortTest {
	private OldDiGraph graph;
	
	@After
	public void tearDown() {
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.depthfirst.TopologicalSort#sort()}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<String, CompleteVertex> vertices = setUpSmallCLRSGraph();
		TopologicalSort topSort = new TopologicalSort(graph);
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("undershorts"), 
				vertices.get("pants")));
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("socks"), 
				vertices.get("shoes")));
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("pants"), 
				vertices.get("belt")));
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("undershorts"), 
				vertices.get("shoes")));
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("pants"), 
				vertices.get("shoes")));
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("shirt"), 
				vertices.get("belt")));
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("shirt"), 
				vertices.get("tie")));
		assertTrue("Proper precedences", topSort.showsAsInOrder(vertices.get("tie"), 
				vertices.get("jacket")));
	}
	/**
	 * Graph from CLRS, p. 613
	 */
	private HashMap<String, CompleteVertex> setUpSmallCLRSGraph() {
		HashMap<String, CompleteVertex> vertices = new HashMap<String, CompleteVertex>();
		vertices.put("undershorts", new CompleteVertex());
		vertices.put("pants", new CompleteVertex());
		vertices.put("belt", new CompleteVertex());
		vertices.put("shirt", new CompleteVertex());
		vertices.put("tie", new CompleteVertex());
		vertices.put("jacket", new CompleteVertex());
		vertices.put("socks", new CompleteVertex());
		vertices.put("shoes", new CompleteVertex());
		vertices.put("watch", new CompleteVertex());
		graph = new OldDiGraph(vertices.values());
		graph.addEdge(vertices.get("undershorts"), vertices.get("pants"));
		graph.addEdge(vertices.get("undershorts"), vertices.get("shoes"));
		graph.addEdge(vertices.get("pants"), vertices.get("belt"));
		graph.addEdge(vertices.get("pants"), vertices.get("shoes"));
		graph.addEdge(vertices.get("socks"), vertices.get("shoes"));
		graph.addEdge(vertices.get("shirt"), vertices.get("belt"));
		graph.addEdge(vertices.get("shirt"), vertices.get("tie"));
		graph.addEdge(vertices.get("belt"), vertices.get("jacket"));
		graph.addEdge(vertices.get("tie"), vertices.get("jacket"));
		return vertices;
	}
}
