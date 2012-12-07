package com.codemelon.graph.vertex.interfaces;


/**
 * Vertex that to another vertex as child to parent
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface ChildVertex extends Vertex {
	/**
	 * Set the parent of the given vertex
	 * @param parent vertex to be set as parent
	 * @throws IllegalArgumentException if the calling vertex does not belong to a graph
	 * @throws IllegalArgumentException if the parent to be set does not belong to the same graph
	 */
	public void setParent(ChildVertex parent);
	/**
	 * Get the vertex's parent
	 * @return the vertex's parent
	 */
	public ChildVertex getParent();
}
