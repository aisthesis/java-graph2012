package com.codemelon.graph.edge.interfaces;

import com.codemelon.graph.common.Color;

/**
 * Requires that an EdgeData object maintain a color parameter
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface EdgeColorData {
	/**
	 * Set the color contained in this EdgeData object
	 * @param color value to which to set the color
	 */
	public void setColor(Color color);
	/**
	 * Retrieve the color of this EdgeData object
	 * @return color stored in this EdgeData object
	 */
	public Color getColor();
}
