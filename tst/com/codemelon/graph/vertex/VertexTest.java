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
	private static final int TEST_NUMBER = 100;
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
		assertFalse("No initial adjacencies", v.hasAdjacencies());
		assertEquals("Color is initially white using default constructor.", Color.WHITE, v.color);
		assertEquals("Color is initialized properly when specified in constructor.", c, u.color);	
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#addAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddDuplicateAdjacency() {
		Vertex u = new Vertex();
		v.addAdjacency(u);
		assertFalse("Method returns false when adjacency already exists", v.addAdjacency(u));
	}
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#addAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testAddManyAdjacencies() {
		addAnonymousAdjacencies(MANY);
		assertEquals("Correct count after adding many vertices", MANY, v.adjacencyCount());
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#removeAdjacency(com.codemelon.graph.vertex.Vertex)}.
	 */
	@Test
	public void testRemoveAdjacency() {
		Vertex u = new Vertex();
		v.addAdjacency(u);
		assertTrue("Returns true when adjacency exists", v.removeAdjacency(u));
		assertFalse("Adding, then removing an adjacency leaves no adjacencies.", v.hasAdjacencies());
		assertFalse("Returns false when adjacency doesn't exist", v.removeAdjacency(new Vertex()));
	}

	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#clearAdjacencies()}.
	 */
	@Test
	public void testClearAdjacencies() {
		addAnonymousAdjacencies(MANY);
		v.clearAdjacencies();
		assertFalse("Clearing adjacencies results in empty adjacency map.", v.hasAdjacencies());
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
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#adjacencyCount()}.
	 */
	@Test
	public void testAdjacencies() {
		addAnonymousAdjacencies(MANY);
		assertEquals("Correct adjacency count after adding many vertices.", MANY, v.adjacencyCount());
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
	
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#parent}.
	 */
	@Test
	public void testParent() {
		Vertex u = new Vertex();
		assertEquals("Parent initialized to null.", null, v.parent);
		v.parent = u;
		assertEquals("Parent subsequently assigned to another vertex.", u, v.parent);
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#parent}.
	 */
	@Test
	public void testDistance() {
		assertEquals("Distance initialized to -1.", -1, v.distance);
		v.distance = TEST_NUMBER;
		assertEquals("Distance can be assigned another number.", TEST_NUMBER, v.distance);
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#discoveryTime}.
	 */
	@Test
	public void testDiscoveryTime() {
		assertEquals("Discovery time initialized to -1.", -1, v.discoveryTime);
		v.discoveryTime = TEST_NUMBER;
		assertEquals("Discovery time can be assigned another number.", TEST_NUMBER, v.discoveryTime);
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#finishTime}.
	 */
	@Test
	public void testFinishTime() {
		assertEquals("Finish time initialized to -1.", -1, v.finishTime);
		v.finishTime = TEST_NUMBER;
		assertEquals("Finish time can be assigned another number.", TEST_NUMBER, v.finishTime);
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.vertex.Vertex#treeNumber}.
	 */
	@Test
	public void testTreeNumber() {
		assertEquals("Tree number initialized to -1.", -1, v.treeNumber);
		v.treeNumber = TEST_NUMBER;
		assertEquals("Tree number can be assigned another number.", TEST_NUMBER, v.treeNumber);
	}
	
	private void addAnonymousAdjacencies(int vertexCount) {
		for (int i = 0; i < vertexCount; i++) {
			v.addAdjacency(new Vertex());
		}
	}
}
