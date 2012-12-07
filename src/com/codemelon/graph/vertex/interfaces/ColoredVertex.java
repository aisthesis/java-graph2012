package com.codemelon.graph.vertex.interfaces;

import com.codemelon.graph.common.Color;

public interface ColoredVertex extends Vertex {
	/**
	 * Set the vertex color
	 * @param color value to which to set the color
	 */
	public void setColor(Color color);
	/**
	 * Get the vertex color
	 * @return the color of the vertex
	 */
	public Color getColor();
}
