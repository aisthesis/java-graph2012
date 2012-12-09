package com.codemelon.graph.common;

/**
 * For coloring vertices or edges. Used in many search algorithms to track
 * which vertices have been visited.
 * @author Marshall Farrier
 * @version 11/24/2012
 */
public enum Color {
	/**
	 * For coloring vertices or edges white. White is the initial (default) color for vertices and edges.
	 */
	WHITE, 
	/**
	 * For coloring vertices or edges gray. Gray is used to indicate that a vertex or edge has
	 * been visited but that a method has not yet finished examining the given object.
	 */
	GRAY,
	/**
	 * For coloring vertices or edges black. Black is used to indicate that a method has
	 * completed its examiniation of an edge or vertex.
	 */
	BLACK;
}
