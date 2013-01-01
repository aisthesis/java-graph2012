package com.codemelon.graph.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.graph.DiGraph;
import com.codemelon.graph.vertex.interfaces.VertexFactory;
import com.codemelon.graph.vertex.types.OrderedDfsVertex;
import com.codemelon.graph.vertex.types.OrderedDfsVertexFactory;
/**
 * @author Marshall Farrier
 * @version Nov 25, 2012
 *
 */
public class TransposerTest {
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	
	private DiGraph<OrderedDfsVertex> graph;
	private ArrayList<OrderedDfsVertex> vertices;
	private VertexFactory<OrderedDfsVertex> vertexFactory;
	private Map<OrderedDfsVertex, OrderedDfsVertex> vertexMap;

	@Before
	public void setUp() {
		vertices = new ArrayList<OrderedDfsVertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.add(new OrderedDfsVertex());
		}
		graph = new DiGraph<OrderedDfsVertex>(vertices);
		vertexFactory = OrderedDfsVertexFactory.INSTANCE;
		vertexMap = null;
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
		Transposer<OrderedDfsVertex> transposer = new Transposer<OrderedDfsVertex>(graph, vertexFactory);
		DiGraph<OrderedDfsVertex> transposedGraph = transposer.getTransposeGraph();
		vertexMap = transposer.getVertexMap();
		assertEquals("Correct number of edges in transposed graph", 
				2, transposedGraph.edgeCount());
		Set<OrderedDfsVertex> transposedVertices = transposedGraph.getVertices();
		// transposedGraph has proper structure
		for (OrderedDfsVertex v : transposedVertices) {
			if (v == vertexMap.get(vertices.get(2))) {
				assertEquals("Vertex 2 is source for 2 edges", 2, v.adjacencyCount());
				assertTrue("Vertex 2 contains adjacency 0", v.containsAdjacency(vertexMap
						.get(vertices.get(0))));
				assertTrue("Vertex 2 contains adjacency 3", v.containsAdjacency(vertexMap
						.get(vertices.get(3))));
			}
			else {
				assertFalse("Only vertex 2 has adjacencies", v.hasAdjacencies());
			}
		}
	}
}