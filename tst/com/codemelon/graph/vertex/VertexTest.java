/**
 * 
 */
package com.codemelon.graph.vertex;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class VertexTest {
	private Vertex v;
	@Before
	public void setUp() {
		v = new Vertex();
	}
	@After
	public void tearDown() {
		v.clearAdjacencies();
	}

	/**
	 * Test method for com.codemelon.graph.vertex.Vertex#Vertex()
	 */
	@Test
	public void testVertex() {
		assertTrue("No initial adjacencies", !v.hasAdjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#addAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddAdjacency() {
		v.addAdjacency(new Vertex());
		assertEquals("1 adjacency after adding vertex", 1, v.adjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#addAllAdjacencies(java.util.Collection)}.
	 */
	@Test
	public void testAddAllAdjacencies() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#removeAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testRemoveAdjacency() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#clearAdjacencies()}.
	 */
	@Test
	public void testClearAdjacencies() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#containsAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testContainsAdjacency() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#adjacencies()}.
	 */
	@Test
	public void testAdjacencies() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#hasAdjacencies()}.
	 */
	@Test
	public void testHasAdjacencies() {
		fail("Not yet implemented");
	}

}
