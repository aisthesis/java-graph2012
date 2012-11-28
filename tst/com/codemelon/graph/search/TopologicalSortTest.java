/**
 * 
 */
package com.codemelon.graph.search;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.vertex.Vertex;

/**
 * @author Marshall Farrier
 * @version Nov 27, 2012
 *
 */
public class TopologicalSortTest {
	private DiGraph graph;
	
	@After
	public void tearDown() {
		graph = null;
	}

	/**
	 * Test method for {@link com.codemelon.graph.util.search.depthfirst.TopologicalSort#sort()}.
	 */
	@Test
	public void testSmallCLRSGraph() {
		HashMap<String, Vertex> vertices = setUpSmallCLRSGraph();
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
	private HashMap<String, Vertex> setUpSmallCLRSGraph() {
		HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
		vertices.put("undershorts", new Vertex());
		vertices.put("pants", new Vertex());
		vertices.put("belt", new Vertex());
		vertices.put("shirt", new Vertex());
		vertices.put("tie", new Vertex());
		vertices.put("jacket", new Vertex());
		vertices.put("socks", new Vertex());
		vertices.put("shoes", new Vertex());
		vertices.put("watch", new Vertex());
		graph = new DiGraph(vertices.values());
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
