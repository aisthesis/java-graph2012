/**
 * 
 */
package com.codemelon.graph.edge;

import static org.junit.Assert.*;

import org.junit.Test;

import com.codemelon.graph.common.Color;
import com.codemelon.graph.common.EdgeType;

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
		assertEquals("Default type correct.", EdgeType.UNKNOWN, edgeData.getType());
	}
	
	/**
	 * Test method for {@link com.codemelon.graph.common.EdgeData#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Color c1 = Color.BLACK;
		Color c2 = Color.GRAY;
		EdgeData edgeData = new EdgeData(c1);
		assertEquals("Color set correctly using constructor.", c1, edgeData.getColor());
		edgeData.setColor(c2);
		assertEquals("Color set correcly using setter", c2, edgeData.getColor());
	}
	
	/**
		 * Test method for {@link com.codemelon.graph.common.EdgeData#getType()}.
		 */
		@Test
		public void testGetType() {
			EdgeType et1 = EdgeType.TREE;
			EdgeType et2 = EdgeType.CROSS;
			EdgeData edgeData = new EdgeData(et1);
			assertEquals("Edge type correct using constructor.", et1, edgeData.getType());
			edgeData.setType(et2);
			assertEquals("Edge type set correcly using setter", et2, edgeData.getType());
		}
	
	/**
	 * Test method for {@link com.codemelon.graph.common.EdgeData#getEdgeWeight()}.
	 */
	@Test
	public void testGetWeight() {
		double weight1 = 3.14159;
		double weight2 = 2.71828;
		EdgeData edgeData = new EdgeData(weight1);
		assertTrue("Edge weight correct using constructor.", approxEqual(weight1, edgeData.getWeight()));
		edgeData.setWeight(weight2);
		assertTrue("Edge type set correcly using setter", approxEqual(weight2, edgeData.getWeight()));
	}
	
	private boolean approxEqual(double x, double y) {
		return Math.abs(x - y) < EdgeConstants.DEFAULT_WEIGHT_EPSILON;
	}
}