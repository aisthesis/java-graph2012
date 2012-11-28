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
		assertEquals("Default color correct.", Color.WHITE, edgeData.getColor());
		assertEquals("Default type correct.", EdgeType.UNKNOWN, edgeData.getEdgeType());
	}
	
	/**
		 * Test method for {@link com.codemelon.graph.common.EdgeData#getColor()}.
		 */
		@Test
		public void testGetColor() {
			Color c = Color.BLACK;
			EdgeData edgeData = new EdgeData(c);
			assertEquals("Explicitly set color correct.", c, edgeData.getColor());
		}

	/**
		 * Test method for {@link com.codemelon.graph.common.EdgeData#getEdgeType()}.
		 */
		@Test
		public void testGetEdgeType() {
			EdgeType et = EdgeType.TREE;
			EdgeData edgeData = new EdgeData(et);
			assertEquals("Explicitly set edge type correct.", et, edgeData.getEdgeType());
		}
}