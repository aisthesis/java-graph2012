/**
 * 
 */
package com.codemelon.graph.common;

/**
 * For coloring vertices or edges. Used in many search algorithms to track
 * which vertices have been visited.
 * @author Marshall Farrier
 * @version 11/24/2012
 */
public enum Color {
	/**
	 * For coloring vertices or edges white
	 */
	WHITE, 
	/**
	 * For coloring vertices or edges gray
	 */
	GRAY,
	/**
	 * For coloring vertices or edges black
	 */
	BLACK;
}
