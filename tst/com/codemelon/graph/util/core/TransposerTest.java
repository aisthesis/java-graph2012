package com.codemelon.graph.util.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.DiGraph;
import com.codemelon.graph.vertex.Vertex;
/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class TransposerTest {
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	private DiGraph graph;
	private ArrayList<Vertex> vertices;

	@Before
	public void setUp() {
		vertices = new ArrayList<Vertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.add(new Vertex(i));
		}
		graph = new DiGraph(vertices);
	}

	@After
	public void tearDown() {
		graph = null;
		vertices = null;
	}
	/**
	 * Test method for {@link com.codemelon.graph.util.core.Transposer#transpose()}.
	 */

	@Test
	public void testTranspose() {
		graph.addEdge(vertices.get(0), vertices.get(2));
		graph.addEdge(vertices.get(3), vertices.get(2));
		DiGraph transposedGraph = new Transposer(graph).transpose();
		assertEquals("Correct number of edges in transposed graph", 
				2, transposedGraph.edgeCount());
		ArrayList<Vertex> transposedVertices = transposedGraph.getVertices();
		// transposedGraph has proper structure
		for (Vertex v : transposedVertices) {
			if (v.label == 2) {
				assertEquals("Vertex 2 is source for 2 edges", 2, v.adjacencyCount());
				assertTrue("Vertex 2 contains adjacency 0", v.containsAdjacency(0));
				assertTrue("Vertex 2 contains adjacency 3", v.containsAdjacency(3));
			}
			else {
				assertFalse("Only vertex 2 has adjacencies", v.hasAdjacencies());
			}
		}
	}

}
