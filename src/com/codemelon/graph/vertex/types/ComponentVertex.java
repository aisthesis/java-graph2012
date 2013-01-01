package com.codemelon.graph.vertex.types;

/**
 * Vertex supporting a component field for distinguishing components
 * of a graph
 * @author Marshall Farrier
 * @version Dec 6, 2012
 */
public interface ComponentVertex extends Vertex {
	/**
	 * Set the number of the component to which the vertex belongs
	 * @param componentNumber value to which to set the component number
	 */
	public void setComponent(int componentNumber);
	/**
	 * Get the number of the component to which the vertex belongs
	 * @return the number of the component to which the vertex belongs
	 */
	public int getComponent();
}
