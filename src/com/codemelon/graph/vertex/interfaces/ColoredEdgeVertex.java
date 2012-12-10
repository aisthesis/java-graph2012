package com.codemelon.graph.vertex.interfaces;

import com.codemelon.graph.common.Color;

/**
 * Vertex that supports colored edges
 * @author Marshall Farrier
 * @version Dec 10, 2012
 */
public interface ColoredEdgeVertex extends Vertex {
	/**
	 * Set the color of the given edge
	 * @param to head of the edge whose color is to be set
	 * @param color value to which to set the edge's color
	 */
	public void setEdgeColor(ColoredEdgeVertex to, Color color);
	/**
	 * Retrieve the color of the given edge
	 * @param to head of the edge whose color is to be retrieved
	 * @return the edge's color
	 */
	public Color getEdgeColor(ColoredEdgeVertex to);
}
