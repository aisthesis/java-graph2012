package com.codemelon.graph.vertex.types;

/**
 * Vertex that maintains a parent relationship to another vertex
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface ChildVertex extends Vertex {
	/**
	 * Set the parent of the given vertex
	 * @param parent vertex to be set as parent
	 */
	public void setParent(ChildVertex parent);
	/**
	 * Get the vertex's parent
	 * @return the vertex's parent
	 */
	public ChildVertex getParent();
}
