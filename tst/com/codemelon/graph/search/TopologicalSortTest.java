package com.codemelon.graph.search;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.graph.types.DiGraph;
import com.codemelon.graph.vertex.types.DfsVertex;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class TopologicalSortTest {
	private DiGraph<DfsVertex> graph;
	
	@After
	public void tearDown() {
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.depthfirst.TopologicalSort#sort()}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<String, DfsVertex> vertices = setUpSmallCLRSGraph();
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
	private HashMap<String, DfsVertex> setUpSmallCLRSGraph() {
		HashMap<String, DfsVertex> vertices = new HashMap<String, DfsVertex>();
		vertices.put("undershorts", new DfsVertex());
		vertices.put("pants", new DfsVertex());
		vertices.put("belt", new DfsVertex());
		vertices.put("shirt", new DfsVertex());
		vertices.put("tie", new DfsVertex());
		vertices.put("jacket", new DfsVertex());
		vertices.put("socks", new DfsVertex());
		vertices.put("shoes", new DfsVertex());
		vertices.put("watch", new DfsVertex());
		graph = new DiGraph<DfsVertex>(vertices.values());
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
