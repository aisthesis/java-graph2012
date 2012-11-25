/**
 * 
 */
package com.codemelon.graph.common;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Marshall Farrier
 * @version Nov 24, 2012
 *
 */
public class EdgeDataTest {

	/**
	 * Test method for {@link com.codemelon.graph.common.EdgeData#EdgeData()}.
	 */
	@Test
	public void testEdgeData() {
		EdgeData edgeData = new EdgeData();
		assertEquals("Default color correct.", Color.WHITE, edgeData.color());
		assertEquals("Default type correct.", EdgeType.UNKNOWN, edgeData.edgeType());
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.common.EdgeData#color()}.
	 */
	@Test
	public void testColor() {
		Color c = Color.BLACK;
		EdgeData edgeData = new EdgeData(c);
		assertEquals("Explicitly set color correct.", c, edgeData.color());
	}

	/**
	 * Test method for {@link com.codemelon.graph.common.EdgeData#edgeType()}.
	 */
	@Test
	public void testEdgeType() {
		EdgeType et = EdgeType.TREE;
		EdgeData edgeData = new EdgeData(et);
		assertEquals("Explicitly set edge type correct.", et, edgeData.edgeType());
	}
}