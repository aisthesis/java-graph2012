/**
 * 
 */
package com.codemelon.graph.vertex;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codemelon.graph.common.Color;

/**
 * @author Marshall Farrier
 * @version 11-24-2012
 */
public class VertexTest {
	private static final int MANY = 1000;
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
		Color c = Color.BLACK;
		Vertex u = new Vertex(c);
		assertTrue("No initial adjacencies", !v.hasAdjacencies());
		assertEquals("Color is initially white using default constructor.", Color.WHITE, v.color);
		assertEquals("Color is initialized properly when specified in constructor.", c, u.color);	
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#addAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddAdjacency() {
		addAnonymousAdjacencies(MANY);
		assertEquals("Correct count after adding many vertices", MANY, v.adjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#removeAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testRemoveAdjacency() {
		Vertex u = new Vertex();
		v.addAdjacency(u);
		v.removeAdjacency(u);
		assertTrue("Adding, then removing an adjacency leaves no adjacencies.", !v.hasAdjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#clearAdjacencies()}.
	 */
	@Test
	public void testClearAdjacencies() {
		addAnonymousAdjacencies(MANY);
		v.clearAdjacencies();
		assertTrue("Clearing adjacencies results in empty adjacency map.", !v.hasAdjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#containsAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testContainsAdjacency() {
		Vertex u = new Vertex();
		v.addAdjacency(u);
		assertTrue("Adjacency map contains added adjacency.", v.containsAdjacency(u));
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#adjacencies()}.
	 */
	@Test
	public void testAdjacencies() {
		addAnonymousAdjacencies(MANY);
		assertEquals("Correct adjacency count after adding many vertices.", MANY, v.adjacencies());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#hasAdjacencies()}.
	 */
	@Test
	public void testHasAdjacencies() {
		v.addAdjacency(new Vertex());
		assertTrue("Vertex has adjacencies after adding 1.", v.hasAdjacencies());
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#color}.
	 */
	@Test
	public void testColor() {
		Color c = Color.GRAY;
		v.color = c;
		assertEquals("Color modified appropriately.", c, v.color);
	}
	
	private void addAnonymousAdjacencies(int vertexCount) {
		for (int i = 0; i < vertexCount; i++) {
			v.addAdjacency(new Vertex());
		}
	}
}
