package com.codemelon.graph.edge;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;

import com.codemelon.graph.Graph;
import com.codemelon.graph.vertex.CompleteVertex;

/**
 * @author Marshall Farrier
 * @version Dec 2, 2012
 *
 */
public class WeightedEdgeTest {
	private static final double CUSTOM_WEIGHT = 2.71828;
	private static final int VERTICES_IN_TEST_GRAPH = 1000;
	HashMap<Integer, CompleteVertex> vertices;
	private Graph graph;

	/**
	 * Set up an undirected graph with various vertices and edge from vertex 0 to vertex 1
	 * and from vertex 2 to vertex 3.
	 * The edge from vetex 0 to vertex 1 is set to a custom weight.
	 */
	@Before
	public void setUp() {
		vertices = new HashMap<Integer, CompleteVertex>(VERTICES_IN_TEST_GRAPH);
		for (int i = 0; i < VERTICES_IN_TEST_GRAPH; i++) {
			vertices.put(i, new CompleteVertex(i));
		}
		graph = new Graph(vertices.values());
		graph.addEdge(vertices.get(0), vertices.get(1));
		graph.setEdgeWeight(vertices.get(0), vertices.get(1), CUSTOM_WEIGHT);
		graph.addEdge(vertices.get(2), vertices.get(3));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() {
		vertices = null;
		graph = null;
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test method for {@link com.codemelon.graph.edge.WeightedEdge#WeightedEdge(com.codemelon.graph.vertex.CompleteVertex, com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test
	public void testWeightedEdgeNullGraph() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(JUnitMatchers.containsString("must belong to a graph"));
		CompleteVertex u = new CompleteVertex();
		CompleteVertex v = new CompleteVertex();
		WeightedEdge e = new WeightedEdge(u, v);
		e.from();
	}

	/**
	 * Test method for {@link com.codemelon.graph.edge.WeightedEdge#WeightedEdge(com.codemelon.graph.vertex.CompleteVertex, com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test
	public void testWeightedEdgeDifferentGraphs() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(JUnitMatchers.containsString("must belong to the same graph"));
		CompleteVertex u = new CompleteVertex();
		Graph g2 = new Graph(1);
		g2.addVertex(u);
		assertEquals("u belongs to g2", g2, u.getGraph());
		assertEquals("0 vertex belongs to graph", graph, vertices.get(0).getGraph());
		WeightedEdge e = new WeightedEdge(u, vertices.get(0));
		e.from();
	}
	/**
	 * Test method for {@link com.codemelon.graph.edge.WeightedEdge#WeightedEdge(com.codemelon.graph.vertex.CompleteVertex, com.codemelon.graph.vertex.CompleteVertex)}.
	 */
	@Test
	public void testWeightedEdgeNoSuchEdge() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(JUnitMatchers.containsString("edge does not exist"));
		WeightedEdge e = new WeightedEdge(vertices.get(2), vertices.get(0));
		e.from();
	}
	/**
	 * Test method for {@link com.codemelon.graph.edge.WeightedEdge#from()}.
	 */
	@Test
	public void testFrom() {
		WeightedEdge e = new WeightedEdge(vertices.get(1), vertices.get(0));
		assertEquals("correct tail", vertices.get(1), e.from());
	}

	/**
	 * Test method for {@link com.codemelon.graph.edge.WeightedEdge#to()}.
	 */
	@Test
	public void testTo() {
		WeightedEdge e = new WeightedEdge(vertices.get(0), vertices.get(1));
		assertEquals("correct head", vertices.get(1), e.to());
	}

	/**
	 * Test method for {@link com.codemelon.graph.edge.WeightedEdge#weight()}.
	 */
	@Test
	public void testWeight() {
		WeightedEdge e = new WeightedEdge(vertices.get(1), vertices.get(0));
		assertTrue("correct weight", graph.areEqualWeights(e.weight(), 
				vertices.get(0).getEdgeWeight(vertices.get(1))));
	}

	/**
	 * Test method for {@link com.codemelon.graph.edge.WeightedEdge#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		WeightedEdge e1 = new WeightedEdge(vertices.get(1), vertices.get(0));
		WeightedEdge e2 = new WeightedEdge(vertices.get(0), vertices.get(1));
		WeightedEdge e3 = new WeightedEdge(vertices.get(2), vertices.get(3));
		assertEquals("equals if same edge", e1, e1);
		assertEquals("equals if same vertices but opposite direction", e1, e2);
		assertFalse("Not equal if different edge", e1.equals(e3));
	}

}
