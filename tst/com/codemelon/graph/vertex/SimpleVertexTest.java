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
public class SimpleVertexTest {
	private SimpleVertex v;
	@Before
	public void setUp() {
		v = new SimpleVertex();
	}
	@After
	public void tearDown() {
		v.clearAdjacencies();
	}

	/**
	 * Test method for com.codemelon.graph.vertex.SimpleVertex#SimpleVertex()
	 */
	@Test
	public void testSimpleVertex() {
		assertTrue("No initial adjacencies", !v.hasAdjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.SimpleVertex#addAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddAdjacency() {
		v.addAdjacency(new SimpleVertex());
		assertEquals("1 adjacency after adding vertex", 1, v.adjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.SimpleVertex#addAllAdjacencies(java.util.Collection)}.
	 */
	@Test
	public void testAddAllAdjacencies() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.SimpleVertex#removeAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testRemoveAdjacency() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.SimpleVertex#clearAdjacencies()}.
	 */
	@Test
	public void testClearAdjacencies() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.SimpleVertex#containsAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testContainsAdjacency() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.SimpleVertex#adjacencies()}.
	 */
	@Test
	public void testAdjacencies() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.SimpleVertex#hasAdjacencies()}.
	 */
	@Test
	public void testHasAdjacencies() {
		fail("Not yet implemented");
	}

}
